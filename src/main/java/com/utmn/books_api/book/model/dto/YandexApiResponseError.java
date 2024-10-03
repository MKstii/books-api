package com.utmn.books_api.book.model.dto;

/**
 * Для обработки ошибок от яндекса
 */
public record YandexApiResponseError(
        String message,
        String description,
        String error) {
}
