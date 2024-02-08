package com.demo.book.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jofo
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * HTTP request blocker
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TraceIdInterceptor());
    }
}