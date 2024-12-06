package com.utmn.books_api.domain.exhibition.repository;

import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
}
