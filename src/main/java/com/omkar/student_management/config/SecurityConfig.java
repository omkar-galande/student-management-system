package com.omkar.student_management.config;

import com.omkar.student_management.entity.User;
import com.omkar.student_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                                .requestMatchers("/auth/register").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/students/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        );
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository,
                                         PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin@123"));
            user.setRole("ADMIN");

            userRepository.save(user);
        };

    }
    

}
