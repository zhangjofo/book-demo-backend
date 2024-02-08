package com.demo.book.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * @author Jofo
 */
@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    /**
     * preHandle is intercept before requesting
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // Add a unique identifier of the request to the MDC in the log framework (used to return the interface and save mysql)
        String traceId = String.valueOf(UUID.randomUUID());

        // Bind the key value to the thread
        MDC.put(FilterGlobalTraceId.TRACE_ID, traceId);
        response.addHeader(FilterGlobalTraceId.TRACE_ID,traceId);

        // Proceed with the interface request
        return true;
    }
}
