package com.utmn.books_api.domain.exhibition.model.response;

import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExhibitionResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<BookSearchResponse> books;
}
