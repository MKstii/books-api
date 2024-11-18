package com.utmn.books_api.domain.history.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryRequest {
    private final LocalDate dateOfIssue;
    private final LocalDate returnDueDate;
    private final Integer booksId;
}