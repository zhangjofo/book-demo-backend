package com.demo.book.aspect;

import java.lang.annotation.*;

/**
 * @author Jofo
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     */
    String description() default "";

}

