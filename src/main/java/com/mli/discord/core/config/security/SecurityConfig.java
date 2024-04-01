package com.mli.discord.core.config.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 
 * @Author D3031104
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            BCryptPasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors().and()
                .csrf().disable() // 禁用CSRF保护
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用无状态会话
                .and()
                .authorizeRequests()
                .antMatchers("/user/check-session", "/user/logout", "/user/login", "/user/find-by-id", "/user/register")
                .permitAll()
                .antMatchers("/chat/export").hasAuthority("ADMIN") // 仅ADMIN角色可以访问
                .antMatchers("/user-to-room/**",
                        "/user-to-group/**", "/send", "/get-messages",
                        "/room/find-all-rooms", "/groups/find-all-groups", "/user/update-password",
                        "/modify-security-question", "/add-security-question")
                .hasAnyAuthority("ADMIN", "NORMAL") // ADMIN和NORMAL角色都可以访问
                .anyRequest().authenticated() // 所有其他请求都需要认证
                .and()
                .httpBasic(); // 使用HTTP Basic认证
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // 允许所有源
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // 允许的方法
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token")); // 允许的请求头
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 对所有API生效
        return source;
    }

}

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
// throws Exception {
// return httpSecurity
// .authorizeRequests(registry -> registry
// .antMatchers("/swagger-ui/**").permitAll()
// .antMatchers("/user/login").permitAll()
// .antMatchers("/user/**").hasAnyAuthority("Champion", "Boss")
// .anyRequest().authenticated())
// .csrf().disable()
// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
// .and()
// .formLogin()
// .and()
// .build();
// }
