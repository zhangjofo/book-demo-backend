package com.demo.book.orm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jofo
 */
@Getter
@Setter
@ToString
public class BookEditDTO extends BookDTO {

    @NotBlank(message = "the id must not be Blank")
    private String id;

    @NotNull(message = "the Book Status must not be null")
    private Integer bookStatus;

}
