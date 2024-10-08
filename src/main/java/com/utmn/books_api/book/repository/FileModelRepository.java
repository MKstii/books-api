package com.utmn.books_api.book.repository;

import com.utmn.books_api.book.model.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileModelRepository extends JpaRepository<FileModel, Long> {

    List<FileModel> findByBookId(long bookId);
}