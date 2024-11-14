package com.utmn.books_api.domain.author.model.entity;

import com.utmn.books_api.domain.book.model.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "author")
@Comment("Автор")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment("ФИО")
    private String name;

    @Column(name = "bio", nullable = false)
    @Comment("Биография")
    private String bio;

    @Column(name = "birth_date", nullable = false)
    @Comment("Дата рождения")
    private LocalDate birthDate;

    @Column(name = "death_date")
    @Comment("Дата смерти")
    private LocalDate deathDate;

    @Column(name = "wikipedia")
    @Comment("Ссылка на википедию")
    private String wikipedia;

    @ManyToMany
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}

