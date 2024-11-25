package com.utmn.books_api.domain.book.controller;

import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.book.model.response.BookResponse;
import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import com.utmn.books_api.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book Controller")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public PagedModel<BookSearchResponse> getBooks(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "subject", required = false) String subject,
            @ParameterObject Pageable pageable

    ){
        var books = bookService.searchBooks(pageable, title, author, subject);
        return new PagedModel<>(books);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id){
        return bookService.getById(id);
    }
}
