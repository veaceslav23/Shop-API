package com.project.carrental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String ALL_PATHS = "/**";
    private static final String ALL_ORIGINS = "*";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping(ALL_PATHS)
            .allowedMethods(GET.name(), HEAD.name(), POST.name(), PUT.name(), DELETE.name(), PATCH.name(), OPTIONS.name())
            .allowedOrigins(ALL_ORIGINS);
    }
}
