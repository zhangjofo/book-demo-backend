package com.demo.book.service.impl;

import com.demo.book.dao.BookRepository;
import com.demo.book.orm.constant.MessageConstant;
import com.demo.book.orm.dto.BookDTO;
import com.demo.book.orm.dto.BookEditDTO;
import com.demo.book.orm.dto.BookQueryDTO;
import com.demo.book.orm.entity.Book;
import com.demo.book.orm.vo.BookVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Jofo
 * title: BookServiceImplTest
 * projectName book-backend
 * description: this is a testBookServiceImpl Test case
 * date 2024/2/3 00030:28
 **/
class BookServiceImplTest {
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookServiceImpl bookServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Book title");
        bookDTO.setIsbn("123456");
        bookDTO.setAuthor("Jofo");

        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        book.setId(1L);
        when(bookRepository.save(any())).thenReturn(book);
        String result = bookServiceImpl.addBook(bookDTO);
        assertEquals(MessageConstant.SUCCESS, result);
    }

    @Test
    void testAddBookFail(){
        when(bookRepository.findById(any())).thenReturn(Optional.of(new Book()));
        when(bookRepository.save(any())).thenReturn(new Book());
        String result = bookServiceImpl.addBook(new BookDTO());
        assertEquals(MessageConstant.FAILURE, result);
    }

    @Test
    void testUpdateBook() {
        BookEditDTO bookDTO = new BookEditDTO();
        bookDTO.setId("MQ==");
        bookDTO.setTitle("Book title");
        bookDTO.setIsbn("123456");
        bookDTO.setAuthor("Jofo");

        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        book.setId(1L);
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);
        String result = bookServiceImpl.updateBook(bookDTO);
        assertEquals(MessageConstant.SUCCESS, result);
    }

    @Test
    void testUpdateBookFail() {
        when(bookRepository.findById(any())).thenReturn(Optional.empty());
        BookEditDTO bookDTO = new BookEditDTO();
        bookDTO.setId("MQ==");
        String result = bookServiceImpl.updateBook(bookDTO);
        assertEquals(MessageConstant.FAILURE, result);
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        String result = bookServiceImpl.deleteBook("MQ==");
        assertEquals(MessageConstant.SUCCESS, result);
    }

    @Test
    void testDeleteBookFail() {
        when(bookRepository.findById(any())).thenReturn(Optional.empty());
        String result = bookServiceImpl.deleteBook("MQ==");
        assertEquals(MessageConstant.FAILURE, result);
    }

    @Test
    void testGetBookById() {
        BookVO bookVO = new BookVO();
        bookVO.setTitle("Book title");
        bookVO.setIsbn("123456");
        bookVO.setAuthor("Jofo");
        bookVO.setBookStatus(1);

        Book book = new Book();
        BeanUtils.copyProperties(bookVO, book);
        book.setId(1L);
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        BookVO result = bookServiceImpl.getBookById("MQ==");
        assertEquals(bookVO.getTitle(), result.getTitle());
    }

    @Test
    void testGetBookByIdNull() {
        when(bookRepository.findById(any())).thenReturn(Optional.empty());
        BookVO result = bookServiceImpl.getBookById("MQ==");
        Assertions.assertNull(result);
    }

    @Test
    void testQueryBookList() {
        BookQueryDTO bookQueryDTO = new BookQueryDTO();
        bookQueryDTO.setTitle("Book title");
        bookQueryDTO.setIsbn("123456");
        bookQueryDTO.setAuthor("Jofo");
        bookQueryDTO.setPageNumber(1);
        bookQueryDTO.setPageSize(10);
        bookQueryDTO.setSortBy("createdTime");
        bookQueryDTO.setSortDirection("DESC");
        Pageable pageable = PageRequest.of(bookQueryDTO.getPageNumber()-1, bookQueryDTO.getPageSize(), Sort.Direction.valueOf(bookQueryDTO.getSortDirection()), bookQueryDTO.getSortBy());
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        BeanUtils.copyProperties(bookQueryDTO, book);
        book.setId(1L);
        book.setBookStatus(1);
        bookList.add(book);
        Page<Book> page = new PageImpl<>(bookList,pageable,1);
        when(bookRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        Page<BookVO> result = bookServiceImpl.queryBookList(bookQueryDTO);
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetBookStatus() {
        Map<Integer, String> result = bookServiceImpl.getBookStatus();
        assertEquals(3, result.size());
    }
}
