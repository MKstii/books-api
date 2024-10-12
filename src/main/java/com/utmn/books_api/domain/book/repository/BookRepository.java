package com.utmn.books_api.domain.book.repository;

import com.utmn.books_api.domain.book.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}