package com.utmn.books_api.domain.exhibition.model.response;

import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(title = "Выставки")
public class ExhibitionResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<BookSearchResponse> books;
}
