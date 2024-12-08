package com.utmn.books_api.domain.exhibition.repository;

import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    @Query("""
            SELECT e FROM exhibition e
            WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))
            OR e.startDate BETWEEN :startStartDay AND :startEndDay
            OR e.endDate BETWEEN :endStartDay AND :endEndDay
            """)
    Page<Exhibition> getAllByFilters(
            @Param("name") String name,
            @Param("startStartDay") LocalDateTime startStartDay, @Param("startEndDay") LocalDateTime startEndDay,
            @Param("endStartDay") LocalDateTime endStartDay, @Param("endEndDay") LocalDateTime endEndDay,
            Pageable pageable
    );
}
