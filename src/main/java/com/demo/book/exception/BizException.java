package com.demo.book.exception;

import lombok.Getter;

/**
 *  @author Jofo
 */
@Getter
public class BizException extends Exception {

    private final String code;

    public BizException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
}
