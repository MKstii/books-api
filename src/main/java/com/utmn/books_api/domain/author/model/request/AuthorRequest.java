package com.utmn.books_api.domain.author.model.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AuthorRequest {
    private String name;
    private String bio;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String wikipedia;
}