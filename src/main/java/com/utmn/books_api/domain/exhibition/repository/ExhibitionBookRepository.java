package com.utmn.books_api.domain.exhibition.repository;

import com.utmn.books_api.domain.exhibition.model.entity.ExhibitionBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionBookRepository extends JpaRepository<ExhibitionBook, Long> {

    Page<ExhibitionBook> findAllByExhibitionId(Long id, Pageable pageable);
}
