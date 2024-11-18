package com.utmn.books_api.domain.book.model.response;

import com.utmn.books_api.domain.author.model.response.AuthorResponse;
import com.utmn.books_api.domain.subject.model.response.SubjectResponse;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookResponseTable {
    private Long id;
    private String title;
    private String subtitle;
    private LocalDate firstPublishDate;
    private String description;
    private List<AuthorResponse> authors;
    private List<SubjectResponse> subjects;
}
