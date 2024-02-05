package com.demo.book.orm.enumeration;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jofo
 */
@Getter
public enum BookStatusEnum {

    AVAILABLE(0, "Available"),
    LOANED(1, "Loan"),
    DISABLED(2, "Disabled");

    private final Integer code;
    private final String name;

    BookStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BookStatusEnum getBookStatusEnum(Integer code) {
        for (BookStatusEnum bookStatusEnum : BookStatusEnum.values()) {
            if (bookStatusEnum.getCode().equals(code)) {
                return bookStatusEnum;
            }
        }
        return null;
    }

    public static Map<Integer, String> getBookStatusEnumMap() {
        Map<Integer, String> map = new HashMap<>();
        for (BookStatusEnum bookStatusEnum : BookStatusEnum.values()) {
            map.put(bookStatusEnum.getCode(), bookStatusEnum.getName());
        }
        return map;
    }
}
