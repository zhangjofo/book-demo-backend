package com.demo.book.controller;

import com.demo.book.orm.constant.MessageConstant;
import com.demo.book.orm.dto.BookDTO;
import com.demo.book.orm.dto.BookEditDTO;
import com.demo.book.orm.dto.BookQueryDTO;
import com.demo.book.orm.entity.ResultCode;
import com.demo.book.orm.vo.BookVO;
import com.demo.book.service.BookService;
import com.demo.book.utils.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * @author Jofo
 * title: BookControllerTest
 * description: this is a testBookController test case
 **/
class BookControllerTest {
    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        when(bookService.addBook(any())).thenReturn(MessageConstant.SUCCESS);
        Result<String> result = bookController.addBook(new BookDTO());
        Assertions.assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    void testUpdateBook() {
        when(bookService.updateBook(any())).thenReturn(MessageConstant.SUCCESS);
        Result<String> result = bookController.updateBook(new BookEditDTO());
        Assertions.assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    void testDeleteBook() {
        when(bookService.deleteBook(anyLong())).thenReturn(MessageConstant.SUCCESS);
        Result<String> result = bookController.deleteBook(1L);
        Assertions.assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    void testGetBookById() {
        when(bookService.getBookById(anyLong())).thenReturn(new BookVO());
        Result<BookVO> result = bookController.getBookById(1L);
        Assertions.assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    void testQueryBookList() {
        Result<Page<BookVO>> result = bookController.queryBookList(new BookQueryDTO());
        Assertions.assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    void testQueryBookStatus() {
        Result<Map<Integer, String>> result = bookController.queryBookStatus();
        Assertions.assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }
}