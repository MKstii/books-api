package com.utmn.books_api.domain.exhibition.controller;

import com.utmn.books_api.domain.exhibition.model.response.ExhibitionBookResponse;
import com.utmn.books_api.domain.exhibition.service.ExhibitionBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/exhibitions/{id}/books")
@RequiredArgsConstructor
@Tag(name = "Exhibitions Books", description = "Книги выставки")
public class ExhibitionBooksController {

    private final ExhibitionBookService exhibitionService;

    @GetMapping
    public PagedModel<ExhibitionBookResponse> get(
            @PathVariable(name = "id") Long id,
            @ParameterObject Pageable pageable) {
        return exhibitionService.get(id, pageable);
    }
}
