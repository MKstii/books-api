package com.utmn.books_api.domain.history.repository;

import com.utmn.books_api.domain.history.model.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends JpaRepository<History, Long> {

    Page<History> findAllByCustomerId(int customerId, Pageable pageable);

    @Query("""
            SELECT h FROM history h
            WHERE h.customer.id = :customerId
                AND h.returnDueDate IS NULL
            """)
    Page<History> findAllByCustomerIdWithoutHistorical(int customerId, Pageable pageable);

    @Query("""
            SELECT h FROM history h
            WHERE h.returnDate < CURRENT_DATE AND h.returnDueDate IS NULL
            """)
    Page<History> findReminders(Pageable pageable);

    Page<History> findByBookId(long bookId, Pageable pageable);
}