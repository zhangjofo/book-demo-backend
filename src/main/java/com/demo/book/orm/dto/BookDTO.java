package com.demo.book.orm.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 *  @author Jofo
 */
@Getter
@Setter
@ToString
public class BookDTO implements Serializable {

    @NotBlank(message = "the title must not be blank")
    @Length(max = 50, message = "the title must be less than 50 characters")
    private String title;

    @NotBlank(message = "the author must not be blank")
    @Length(max = 50, message = "the title must be less than 50 characters")
    private String author;

    @NotNull(message = "the publication year must not be null")
    @DecimalMax(value="9999", message = "the publication year must be less than 9999")
    private Integer publicationYear;

    @NotBlank(message = "the ISBN must not be blank")
    @Length(max = 30, message = "the title must be less than 50 characters")
    private String isbn;
}