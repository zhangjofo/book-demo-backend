package com.demo.book.orm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Jofo
 */
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "author", nullable = false, length = 50)
    private String author;

    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @Column(name = "isbn", nullable = false, length = 30)
    private String isbn;

    @Column(name = "book_status", nullable = false)
    private Integer bookStatus;

    @Column(name = "created_time", nullable = false)
    private Instant createdTime;

    @Column(name = "update_time")
    private Instant updateTime;

}