package com.utmn.books_api.domain.exhibition.model.request;

import com.utmn.books_api.domain.exhibition.model.validator.ExhibitionDateValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(title = "Выставки")
@Data
@ExhibitionDateValidation
public class ExhibitionRequest {

    @NotNull(message = "Название должно быть заполнено.")
    private String name;

    private String description;

    @NotNull(message = "Дата начала должна быть заполнена.")
    private LocalDate startDate;

    @NotNull(message = "Дата окончания должна быть заполнена.")
    private LocalDate endDate;
    
    private List<ExhibitionBookRequest> books;
}
