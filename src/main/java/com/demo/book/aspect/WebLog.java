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
     * Description of the log
     */
    String description() default "";

}

