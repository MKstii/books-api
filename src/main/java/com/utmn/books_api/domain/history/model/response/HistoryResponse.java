package com.utmn.books_api.domain.history.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryResponse {
    private final Long id;
    private final LocalDate dateOfIssue;
    private final LocalDate returnDueDate;
    private final LocalDate returnDate;
}