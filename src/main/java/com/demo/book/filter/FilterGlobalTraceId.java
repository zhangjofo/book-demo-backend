package com.demo.book.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Jofo
 */
public class FilterGlobalTraceId implements Filter {

    public static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String oldTraceId = httpRequest.getHeader(TRACE_ID);

            if(StringUtils.isNotBlank(oldTraceId)){
                MDC.put(TRACE_ID,oldTraceId);
            }else {
                MDC.put(TRACE_ID, generateTraceId());
            }
            chain.doFilter(request, response);
        } finally {
            // Clear the MDC traceId value to ensure that the logs of other requests are not affected after the request is complete
            MDC.remove(TRACE_ID);
        }
    }

    @Override
    public void destroy() {
        // Cleanup operation
    }

    private String generateTraceId() {
        // Generate a unique trace Id here and return it
        return UUID.randomUUID().toString();
    }
}