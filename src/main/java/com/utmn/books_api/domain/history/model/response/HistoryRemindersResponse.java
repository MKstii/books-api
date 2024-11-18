package com.utmn.books_api.domain.history.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryRemindersResponse {
    private final Long id;
    private final String title;
    private final String customerName;
    private final LocalDate dateOfIssue;
    private final LocalDate returnDate;
}