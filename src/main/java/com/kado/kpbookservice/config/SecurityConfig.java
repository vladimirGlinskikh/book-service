package com.kado.kpbookservice.config;


import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@Log4j2
public class SecurityConfig {

    @Value("${keycloak.issue-url}")
    private static String issueUrl;


    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        return null;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .anyRequest()
//                .permitAll();
//
//        http
//                .cors(
//                        cors -> cors
//                                .configurationSource(request -> {
//                                    CorsConfiguration corsConfiguration = new CorsConfiguration();
//                                    corsConfiguration.setAllowedOrigins(List.of("*"));
//                                    corsConfiguration.setAllowedMethods(List.of("*"));
//                                    corsConfiguration.setAllowedHeaders(List.of("*"));
//                                    return corsConfiguration.applyPermitDefaultValues();
//                                })
//                );
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(withDefaults())
                .oauth2Login(withDefaults())
                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // пользователи для тестовой авторизации
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();

        var nikita = new User("Nikita",
                passwordEncoder()
                        .encode("123"),
                Collections.emptyList());

        var vladimir = User.builder()
                .username("Vladimir")
                .password(passwordEncoder()
                        .encode("234"))
                .authorities("USER")
                .authorities("read")
                .build();

        users.createUser(nikita);
        users.createUser(vladimir);

        return users;
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successLogger() {
        return event -> {
            log.info("success: {}", event.getAuthentication());
        };
    }
}
