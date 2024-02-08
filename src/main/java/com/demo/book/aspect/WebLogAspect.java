package com.demo.book.aspect;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @author Jofo
 **/
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /** Line breaks */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** Use a custom @WebLog annotation as a tangent point */
    @Pointcut("@annotation(com.demo.book.aspect.WebLog)")
    public void webLog() {}

    /**
     * Weave in before cutting points
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // Start printing the request logs
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }

        // Get descriptive information for @WebLog annotations
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("========== Start ==========");
        // 打印请求 url
        if (request != null) {
            log.info("URL            : {}", request.getRequestURL());
        }
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        if (request != null) {
            log.info("HTTP Method    : {}", request.getMethod());
        }
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        if (request != null) {
            log.info("IP             : {}", request.getRemoteAddr());
        }
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSON(joinPoint.getArgs()));
    }

    /**
     * Weave after the cut point
     */
    @After("webLog()")
    public void doAfter() {
        // After the interface is finished, the line wrap is convenient for segmentation and viewing
        log.info("========== End ========== {}",LINE_SEPARATOR);
    }

    /**
     * SURROUND
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // Print out the parameters
        log.info("Response Args  : {}", JSON.toJSON(result));
        // Time taken to execute
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


    /**
     * Get a description of the slice annotation
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}
