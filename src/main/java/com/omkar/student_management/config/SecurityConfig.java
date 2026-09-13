package com.omkar.student_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/students/health").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/students/**").hasRole("ADMIN")
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin@123")
                .roles("ADMIN")
                .build();

        UserDetails student = User.builder()
                .username("student")
                .password("{noop}student@123")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(admin, student);

    }

}
