package com.demo.book.service;

import com.demo.book.orm.dto.BookDTO;
import com.demo.book.orm.dto.BookEditDTO;
import com.demo.book.orm.dto.BookQueryDTO;
import com.demo.book.orm.vo.BookVO;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author Jofo
 * title: BookService
 * description: this is BookService is Book
 * date 2024/2/1 000121:29
 **/
public interface BookService {
    String addBook(BookDTO bookDTO);

    String updateBook(BookEditDTO bookDTO);

    String deleteBook(Long id);

    BookVO getBookById(Long id);

    Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO);

    Map<Integer, String> getBookStatus();
}
