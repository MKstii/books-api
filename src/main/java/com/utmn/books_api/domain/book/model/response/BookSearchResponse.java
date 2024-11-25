package com.utmn.books_api.domain.book.model.response;

import com.utmn.books_api.domain.author.model.response.AuthorShortResponse;
import lombok.Data;

import java.util.List;

@Data
public class BookSearchResponse {
    Long id;
    String title;
    List<AuthorShortResponse> authors;
}
