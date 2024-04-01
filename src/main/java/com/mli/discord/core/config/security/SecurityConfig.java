package com.mli.discord.core.config.security;

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

/**
 * SpringSecurity 5.4.x以上新用法配置
 * 为避免循环依赖，仅用于配置HttpSecurity
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

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
        // .antMatchers("/swagger-ui/**").permitAll()
        // .antMatchers("/user/login").permitAll();
        // .antMatchers("/user/**").hasAnyAuthority("ADMIN");
        // .and()
        // .formLogin(); // 開啟內建登入畫面 (測試用途)
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}