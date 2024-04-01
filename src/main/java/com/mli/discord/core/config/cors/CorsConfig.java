package com.mli.discord.core.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 跨來源資源共享配置
 * 此類配置了跨來源資源共享（CORS）以允許跨域請求。
 * 
 * @Author D3031104
 * @version 1.0
 */
@Configuration
public class CorsConfig {
    /**
     * CORS 配置
     * 
     * @return WebMvcConfigurer 包含 CORS 配置的物件
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "OPTIONS", "DELETE")
                        .allowedHeaders("Content-Type", "Access-Control-Allow-Headers", "Authorization",
                                "X-Requested-With");
            }
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8090"); // 允许的前端应用地址
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
