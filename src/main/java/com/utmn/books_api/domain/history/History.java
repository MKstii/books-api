package com.utmn.books_api.domain.history;

import com.utmn.books_api.auth.model.entity.AppUser;
import com.utmn.books_api.auth.model.entity.Customer;
import com.utmn.books_api.domain.book.model.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Getter
@Setter
@Comment("")
@Entity(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateOfIssue")
    @Comment("")
    private LocalDate dateOfIssue;

    @Column(name = "returnDueDate")
    @Comment("")
    private LocalDate returnDueDate;

    @Column(name = "returnDate")
    @Comment("")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

