package com.utmn.books_api.domain.book.repository;

import com.utmn.books_api.domain.book.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT DISTINCT b FROM book b " +
            "JOIN b.authors a " +
            "JOIN b.subjects s " +
            "WHERE (LOWER(a.name) LIKE LOWER(CONCAT('%', COALESCE(:author, ''), '%'))) " +
            "AND (LOWER(b.title) LIKE LOWER(CONCAT('%', COALESCE(:title, ''), '%'))) " +
            "AND (LOWER(s.name) LIKE LOWER(CONCAT('%', COALESCE(:subject, ''), '%')))")
    Page<Book> findByTitleAuthorSubject(
            Pageable pageable,
            @Param("title")String title,
            @Param("author")String author,
            @Param("subject")String subject);
}