package com.demo.book.orm.entity;

import com.demo.book.utils.IResultCode;
import lombok.Getter;

/**
 * @author Jofo
 * title: ResultCode
 **/
@Getter
public enum ResultCode implements IResultCode {

    SUCCESS(10200, "SUCCESS"),
    FAILURE(10500, "FAILURE"),
    VALIDATE_ERROR(10501, "VALIDATE_ERROR"),
    RESULT_ERROR(10600, "RESULT_ERROR"),
    RESULT_FAIL(10500, "RESULT_FAIL");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
