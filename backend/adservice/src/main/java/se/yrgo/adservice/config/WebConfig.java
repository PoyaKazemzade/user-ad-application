package se.yrgo.adservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from all origins or specify your frontend URL
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173") // The origin of your frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("application/json")
                .allowCredentials(true); // Allow credentials (cookies, etc.)
    }
}
