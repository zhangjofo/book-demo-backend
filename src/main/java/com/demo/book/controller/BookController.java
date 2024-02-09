package com.demo.book.controller;

import com.demo.book.aspect.WebLog;
import com.demo.book.orm.dto.BookDTO;
import com.demo.book.orm.dto.BookEditDTO;
import com.demo.book.orm.dto.BookQueryDTO;
import com.demo.book.orm.vo.BookVO;
import com.demo.book.service.BookService;
import com.demo.book.utils.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * title:   BookController
 * projectName  book-backend
 * description:  this is controller for book
 *
 * @author Jofo
 * date 2024/2/1 000121:37
 **/
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    /**
     * description: BookController add book
     */
    @WebLog(description = "add book")
    @PostMapping("/add")
    public Result<String> addBook(@Valid @RequestBody BookDTO bookDTO) {
        return Result.data(bookService.addBook(bookDTO));
    }

    /**
     * description: BookController update book
     */
    @WebLog(description = "update book")
    @PutMapping("/update")
    public Result<String> updateBook(@Valid @RequestBody BookEditDTO bookDTO) {
        return Result.data(bookService.updateBook(bookDTO));
    }


    /**
     * description: BookController delete book
     */
    @WebLog(description = "delete book")
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteBook(@PathVariable("id") String id) {
        return Result.data(bookService.deleteBook(id));
    }

    /**
     * description: BookController get book by id
     */
    @WebLog(description = "get book details")
    @GetMapping("/get/{id}")
    public Result<BookVO> getBookById(@PathVariable("id") String id) {
        return Result.data(bookService.getBookById(id));
    }

    /**
     * description: BookController query book list
     */
    @WebLog(description = "query book list")
    @PostMapping("/list")
    public Result<Page<BookVO>> queryBookList(@Valid @RequestBody BookQueryDTO bookQueryDTO) {
        return Result.data(bookService.queryBookList(bookQueryDTO));
    }

    /**
     * description: BookController get book status
     */
    @WebLog(description = "get all of book status")
    @GetMapping("/bookStatus")
    public Result<Map<Integer,String>> queryBookStatus() {
        return Result.data(bookService.getBookStatus());
    }
}
