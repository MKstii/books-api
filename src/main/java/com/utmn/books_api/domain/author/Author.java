package com.utmn.books_api.domain.author;

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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment("")
    private String name;

    @Column(name = "bio", nullable = false)
    @Comment("")
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

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}

