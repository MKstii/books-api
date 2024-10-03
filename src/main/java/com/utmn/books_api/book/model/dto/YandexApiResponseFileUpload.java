package com.utmn.books_api.book.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Только ради ссылки эта обертка
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record YandexApiResponseFileUpload(String href) {
}
