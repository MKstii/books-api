package com.utmn.books_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bio;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String wikipedia;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;


}

