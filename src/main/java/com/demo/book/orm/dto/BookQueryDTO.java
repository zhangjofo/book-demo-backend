package com.demo.book.orm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  @author Jofo
 */
@Getter
@Setter
@ToString
public class BookQueryDTO {

    private String title;

    private String author;

    private String isbn;

    @NotNull(message = "pageNum must not be null")
    private int pageNumber;

    @NotNull(message = "pageSize must not be null")
    private int pageSize;

    @NotBlank(message = "sortBy must not be blank")
    private String sortBy;

    private String sortDirection = "DESC";

}
