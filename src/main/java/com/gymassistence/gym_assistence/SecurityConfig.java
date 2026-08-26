package com.gymassistence.gym_assistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/guardarUsuario", "/registrarCuenta", "/loginCuenta")
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/css/**",
                    "/js/**",
                    "/guardarUsuario",
                    "/registrarCuenta",
                    "/loginCuenta"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                .loginPage("/")
                .defaultSuccessUrl("/panel", true)
            );

        return http.build();
    }
}