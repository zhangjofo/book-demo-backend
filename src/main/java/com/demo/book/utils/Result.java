package com.demo.book.utils;

import com.demo.book.orm.constant.MessageConstant;
import com.demo.book.orm.entity.ResultCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

/**
 *  @author Jofo
 */
@Getter
@Setter
public class Result<T>{
    /**
     * Status, 10200 for success, 10500 for failure
     */
    private int code;
    /**
     * Indicative information
     */
    private String message;
    /**
     * timestamp
     */
    private long timestamp;
    /**
     * Whether it was successful or not
     */
    private boolean isSuccess;
    /**
     * Data Body
     */
    private T data;
    /**
     * trace id
     */
    private String traceId;



    private Result(int code, T data, String message) {
        this.isSuccess = true;
        this.code = code;
        this.data = data;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.isSuccess = code == 200 || code == 10200;
        this.traceId = MDC.get("traceId") == null ? "" : MDC.get("traceId");
    }


    public static <T> Result<T> data(T data) {
        return data(data, MessageConstant.SUCCESS);
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.getCode(), data, msg);
    }

    public static <T> Result<T> data(int code, T data, String msg) {
        return new Result<>(code, data, data == null ? "The bearer data is empty" : msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, null, msg);
    }
}
