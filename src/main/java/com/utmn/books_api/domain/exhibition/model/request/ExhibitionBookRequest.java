package com.utmn.books_api.domain.exhibition.model.request;

import com.utmn.books_api.domain.base.ObjectWrapper;
import lombok.Data;

@Data
public class ExhibitionBookRequest {

    private ObjectWrapper book;
    private int displayPosition;
    private String notes;
}
