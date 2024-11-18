package com.utmn.books_api.domain.history.model.entity;

import com.utmn.books_api.domain.customer.model.entity.Customer;
import com.utmn.books_api.domain.book.model.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Getter
@Setter
@Comment("Заказ")
@Entity(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Идентификатор")
    private Long id;

    @Column(name = "date_of_issue")
    @Comment("Дата получения книги")
    private LocalDate dateOfIssue;

    @Column(name = "return_due_date")
    @Comment("Дата возвращения книги")
    private LocalDate returnDueDate;

    @Column(name = "return_date")
    @Comment("Фактическая дата возвращения")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

