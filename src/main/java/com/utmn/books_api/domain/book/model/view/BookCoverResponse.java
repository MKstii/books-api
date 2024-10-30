package com.utmn.books_api.domain.book.model.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCoverResponse {

    private Long id;
    private String mimeType;
    private String path;
}