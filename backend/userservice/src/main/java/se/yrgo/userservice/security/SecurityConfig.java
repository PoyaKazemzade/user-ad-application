package se.yrgo.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Tillåt H2 Console utan CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Tillåt åtkomst till H2 Console
                        .anyRequest().authenticated() // Alla andra endpoints kräver autentisering
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Tillåt iframes för H2 Console
                .formLogin(form -> form.permitAll()); // Tillåt formulärbaserad inloggning (om du vill ha det)
        return http.build();
    }
}
