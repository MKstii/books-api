package com.utmn.books_api.domain.author.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private String bio;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String wikipedia;
}