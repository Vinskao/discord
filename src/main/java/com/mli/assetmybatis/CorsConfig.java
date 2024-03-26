package com.mli.assetmybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
