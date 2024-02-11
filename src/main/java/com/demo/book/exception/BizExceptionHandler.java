package com.demo.book.exception;

import com.demo.book.orm.entity.ResultCode;
import com.demo.book.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @author Jofo
 */
@RestControllerAdvice
@Slf4j
public class BizExceptionHandler {

    private static final String MESSAGE = "Program error,stack message:";

    @ExceptionHandler(value = org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseBody
    public Result<String> globalExceptionHandler(org.springframework.dao.DataIntegrityViolationException e) {
        log.error(MESSAGE, e);
        return Result.fail( ResultCode.RESULT_ERROR.getCode(), "data Duplicate, please check");
    }

    @ExceptionHandler(value = org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> globalExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException e) {
        log.error(MESSAGE, e);
        return Result.fail( ResultCode.VALIDATE_ERROR.getCode(), e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
    }

    @ExceptionHandler(value = java.lang.IllegalArgumentException.class)
    @ResponseBody
    public Result<String> globalExceptionHandler(java.lang.IllegalArgumentException e) {
        log.error(MESSAGE, e);
        return Result.fail( ResultCode.VALIDATE_ERROR.getCode(), "this data is not in the DB,please check");
    }

    /**
     * Global anomalies
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> globalExceptionHandler(Exception e) {
        log.error(MESSAGE, e);
        return Result.fail(ResultCode.RESULT_FAIL.getCode(), "The service is abnormal");
    }
}
