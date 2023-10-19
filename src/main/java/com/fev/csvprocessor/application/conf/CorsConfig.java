package com.fev.csvprocessor.application.conf;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Value("${custom.cors.allowed-origins}")
    private String[] allowedOrigins;
    @Value("${custom.cors.allowed-methods}")
    private String[] allowedMethods;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(allowedMethods)
                        .allowedOrigins(allowedOrigins)
                        .allowedHeaders("*")
                        .maxAge(3600)
                        .allowCredentials(false);
            }
        };
    }

}
