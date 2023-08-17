package com.example.todoist.config;

import com.example.todoist.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .sessionManagement(
                        configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        matcherRegistry ->
                                matcherRegistry
                                        .requestMatchers(
                                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/tasks/**"),
                                                AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/tasks/**"),
                                                AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/tasks/**"),
                                                AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/users/**")

                                        )
                                        .hasRole(Role.ADMIN.name())

                                        .requestMatchers(HttpMethod.GET, "/tasks/**")
                                        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())

                                        .requestMatchers("/**").permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
