package com.demo.book.service.impl;

import com.demo.book.dao.BookRepository;
import com.demo.book.orm.constant.BookConstant;
import com.demo.book.orm.constant.MessageConstant;
import com.demo.book.orm.dto.BookDTO;
import com.demo.book.orm.dto.BookEditDTO;
import com.demo.book.orm.dto.BookQueryDTO;
import com.demo.book.orm.entity.Book;
import com.demo.book.orm.enumeration.BookStatusEnum;
import com.demo.book.orm.vo.BookVO;
import com.demo.book.service.BookService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.time.Instant.now;

/**
 *  @author Jofo
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookRepository bookRepository;

    /**
     *  Add a book
     */
    @Override
    public String addBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        book.setCreatedTime(now());
        book.setBookStatus(BookStatusEnum.AVAILABLE.getCode());
        book = bookRepository.save(book);
        if (book.getId() != null) {
            return MessageConstant.SUCCESS;
        }
        return MessageConstant.FAILURE;
    }

    /**
     * Update a book
     */
    @Override
    public String updateBook(BookEditDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(bookDTO.getId());
        if (book.isPresent()) {
            BeanUtils.copyProperties(bookDTO, book.get());
            book.get().setUpdateTime(now());
            bookRepository.save(book.get());
            return MessageConstant.SUCCESS;
        }
        return MessageConstant.FAILURE;
    }

    /**
     * Delete a book
     */
    @Override
    public String deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return MessageConstant.SUCCESS;
        }
        return MessageConstant.FAILURE;
    }

    /**
     * get a book
     */
    @Override
    public BookVO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book.get(), bookVO);
            bookVO.setBookStatusName(Objects.requireNonNull(BookStatusEnum.getBookStatusEnum(book.get().getBookStatus())).getName());
            return bookVO;
        }
        return null;
    }

    /**
     * query book list
     */
    @Override
    public Page<BookVO> queryBookList(BookQueryDTO bookQueryDTO) {
        Specification<Book> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(bookQueryDTO.getTitle())){
                predicates.add(criteriaBuilder.like(root.get(BookConstant.TITLE), "%" + bookQueryDTO.getTitle() + "%"));
            }
            if(StringUtils.isNotBlank(bookQueryDTO.getAuthor())){
                predicates.add(criteriaBuilder.like(root.get(BookConstant.AUTHOR), "%" + bookQueryDTO.getAuthor() + "%"));
            }
            if(StringUtils.isNotBlank(bookQueryDTO.getIsbn())){
                predicates.add(criteriaBuilder.like(root.get(BookConstant.ISBN), "%" + bookQueryDTO.getIsbn() + "%"));
            }
            Predicate[] p = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(p);
        };
        Pageable pageable = PageRequest.of(bookQueryDTO.getPageNumber()-1, bookQueryDTO.getPageSize(), Sort.Direction.valueOf(bookQueryDTO.getSortDirection()), bookQueryDTO.getSortBy());
        Page<Book> page = bookRepository.findAll(specification,pageable);
        List<BookVO> bookVOList = new ArrayList<>();
        page.getContent().forEach(book->{
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            bookVO.setBookStatusName(Objects.requireNonNull(BookStatusEnum.getBookStatusEnum(book.getBookStatus())).getName());
            bookVOList.add(bookVO);
        });
        return new PageImpl<>(bookVOList,pageable,page.getTotalElements());
    }

    /**
     * get the book status
     */
    @Override
    public Map<Integer, String> getBookStatus() {
        return BookStatusEnum.getBookStatusEnumMap();
    }

}
