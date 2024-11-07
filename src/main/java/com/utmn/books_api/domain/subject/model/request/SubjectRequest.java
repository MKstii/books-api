package com.utmn.books_api.domain.subject.model.request;

import jakarta.validation.constraints.NotNull;

public record SubjectRequest(
        @NotNull(message = "Навание должно быть заполнено") String name) {
}