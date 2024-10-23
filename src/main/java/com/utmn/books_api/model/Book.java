package com.utmn.books_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    private LocalDate firstPublishDate;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "BooksAuthors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @OneToOne
    @JoinColumn(name = "bookCover_id")
    private BookCover bookCover;

    @OneToOne
    @JoinColumn(name = "bookSubject_id")
    private BookSubject bookSubject;

    @OneToMany(mappedBy = "book")
    private List<History> histories;
}

