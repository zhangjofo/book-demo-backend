package com.demo.book.utils;

import com.demo.book.orm.constant.MessageConstant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Jofo
 */
class ResultTest {

    @Test
    void testSuccess() {
        // Arrange
        int code = 200;
        String data = "success";
        String message = MessageConstant.SUCCESS;

        // Act
        Result<String> result = Result.data(code, data, message);

        result.setCode(code);
        long timestamp = System.currentTimeMillis();
        result.setTimestamp(timestamp);
        result.setMessage(message);
        result.setTraceId("123456");
        result.setSuccess(true);
        result.setData("data");
        // Assert
        assertEquals(code, result.getCode());
        assertEquals("data", result.getData());
        assertEquals(message, result.getMessage());
        assertEquals("123456", result.getTraceId());
        assertEquals(timestamp, result.getTimestamp());
        assertTrue(result.isSuccess());
    }

    @Test
    void testFail() {
        // Arrange
        int code = 404;
        String message = "Not Found";

        // Act
        Result<String> result = Result.fail(code, message);

        // Assert
        assertEquals(code, result.getCode());
        assertNull(result.getData());
        assertEquals(message, result.getMessage());
        assertFalse(result.isSuccess());
    }
}
