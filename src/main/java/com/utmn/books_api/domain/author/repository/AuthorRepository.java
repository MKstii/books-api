package com.utmn.books_api.domain.author.repository;

import com.utmn.books_api.domain.author.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}