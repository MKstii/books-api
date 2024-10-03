package com.utmn.books_api.book.model.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileModelView {

    private Long id;
    private String mimeType;
    private String path;
}