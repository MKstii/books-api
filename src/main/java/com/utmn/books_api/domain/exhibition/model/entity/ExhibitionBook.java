package com.utmn.books_api.domain.exhibition.model.entity;

import com.utmn.books_api.domain.book.model.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "exhibition_book")
public class ExhibitionBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
    private Exhibition exhibition;

    @Column(name = "display_position")
    private int displayPosition;

    @Column(name = "notes")
    private String notes;
}
