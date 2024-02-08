package com.demo.book.config;

import com.demo.book.filter.FilterGlobalTraceId;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jofo
 */
@Configuration
public class GlobalWebTraceIdConfig {
    @Bean
    public FilterRegistrationBean<FilterGlobalTraceId> loggingFilter() {
        FilterRegistrationBean<FilterGlobalTraceId> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FilterGlobalTraceId());
        // Set the URL filtering mode
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}