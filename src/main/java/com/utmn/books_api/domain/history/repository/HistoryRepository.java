package com.utmn.books_api.domain.history.repository;

import com.utmn.books_api.domain.history.model.entity.History;
import jakarta.persistence.OrderBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends JpaRepository<History, Long> {

    Page<History> findAllByCustomerId(int customerId, Pageable pageable);

    @Query("""
            SELECT h FROM history h
            WHERE h.customer.id = :customerId
                AND h.returnDate IS NULL
            """)
    Page<History> findAllByCustomerIdWithoutHistorical(int customerId, Pageable pageable);

    @Query("""
            SELECT h FROM history h
            WHERE h.returnDueDate < CURRENT_DATE AND h.returnDate IS NULL
            """)
    Page<History> findReminders(Pageable pageable);

    @Query(
            """
            SELECT h FROM history h
            WHERE h.customer.id = :customerId
            AND h.book.id = :bookId
            AND h.returnDate is NULL
            """
    )
    History findNotReturnedHistory(int customerId, long bookId);

    @OrderBy("dateOfIssue")
    Page<History> findByBookId(long bookId, Pageable pageable);
}