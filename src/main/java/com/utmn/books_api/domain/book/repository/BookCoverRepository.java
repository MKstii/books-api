package com.utmn.books_api.domain.book.repository;

import com.utmn.books_api.domain.book.model.entity.BookCover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    List<BookCover> findByBookId(long bookId);
}