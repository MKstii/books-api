package com.utmn.books_api.domain.exhibition.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(title = "Книга выставки")
public class ExhibitionBookResponse {

    @Schema(title = "Идентификатор")
    private Long id;

    @Schema(title = "Название книги")
    private String title;

    @Schema(title = "Дата первой публикации")
    private LocalDate firstPublishDate;

    @Schema(title = "Описание")
    private String description;

    @Schema(title = "Авторы")
    private String authors;

    @Schema(title = "Темы")
    private String subjects;
}
