package com.demo.book.orm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 *  @author Jofo
 */
@Getter
@Setter
@ToString
public class BookVO {

    private Long id;

    private String title;

    private String author;

    private Integer publicationYear;

    private String isbn;

    private Integer bookStatus;

    private String bookStatusName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant updateTime;
}
