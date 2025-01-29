package com.xsis.config;

import com.xsis.util.authentication.SessionAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilter(new SessionAuthenticationFilter())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/category/**", "/variant/**", "/product/**").hasRole("ADMIN")
                        .requestMatchers("/order/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

}
