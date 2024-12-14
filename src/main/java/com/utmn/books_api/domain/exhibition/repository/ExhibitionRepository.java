package com.utmn.books_api.domain.exhibition.repository;

import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    @Query("""
            SELECT e FROM exhibition e
            WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    Page<Exhibition> findAllByName(@Param("name") String name, Pageable pageable);

    Page<Exhibition> findAllByStartDateGreaterThanEqual(LocalDate startDate, Pageable pageable);

    Page<Exhibition> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
