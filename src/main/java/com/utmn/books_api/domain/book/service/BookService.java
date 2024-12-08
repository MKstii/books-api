package com.utmn.books_api.domain.book.service;

import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.book.model.mapper.BooksMapper;
import com.utmn.books_api.domain.book.model.response.BookResponse;
import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import com.utmn.books_api.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BooksMapper booksMapper;
    private final BookFileService bookFileService;

    public Book getEntity(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Клиент с идентификатором `%s` не найден".formatted(id)));
    }

    public Page<BookSearchResponse> searchBooks(Pageable pageable, String title, String author, String subject) {
        var books = bookRepository.findByTitleAuthorSubject(pageable, title, author, subject);
        return books.map(booksMapper::toSearchResponse);
    }

    public BookResponse getById(Long id) {
        var entity = getEntity(id);
        var response = booksMapper.toBookResponse(entity);
        response.getCovers().forEach(bookFileService::refreshDownloadPath);
        return response;
    }
}
