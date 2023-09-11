package com.kado.kpbookservice.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    @Value("${keycloak.issue-url}")
    private static String issueUrl;


    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        return null;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();

        http
                .cors(
                        cors -> cors
                                .configurationSource(request -> {
                                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                                    corsConfiguration.setAllowedOrigins(List.of("*"));
                                    corsConfiguration.setAllowedMethods(List.of("*"));
                                    corsConfiguration.setAllowedHeaders(List.of("*"));
                                    return corsConfiguration.applyPermitDefaultValues();
                                })
                );
        return http.build();
    }
}
