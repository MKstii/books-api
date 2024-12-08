package com.utmn.books_api.domain.exhibition.controller;

import com.utmn.books_api.domain.exhibition.model.request.ExhibitionRequest;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionBookResponse;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import com.utmn.books_api.domain.exhibition.service.ExhibitionBookService;
import com.utmn.books_api.domain.exhibition.service.ExhibitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RestController
@RequestMapping("/exhibitions")
@RequiredArgsConstructor
@Tag(name = "Exhibitions", description = "Выставки")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    private final ExhibitionBookService exhibitionBookService;

    @GetMapping
    public PagedModel<ExhibitionResponse> get(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @ParameterObject Pageable pageable) {
        return exhibitionService.get(name, startDate, endDate, pageable);
    }

    @GetMapping("/{id}")
    public ExhibitionResponse getExhibitionById(@PathVariable Long id) {
        return exhibitionService.getById(id);
    }

    @PostMapping
    public ExhibitionResponse create(@RequestBody ExhibitionRequest request) {
        return exhibitionService.create(request);
    }

    @PutMapping("/{id}")
    public ExhibitionResponse update(@PathVariable Long id, @RequestBody @Valid ExhibitionRequest request) {
        return exhibitionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exhibitionService.delete(id);
    }

    @Operation(description = """
            Привет аналитикам и за их великолепную аналитику.
            Метод добавил, чтобы получить все книги, которые есть на выставках.
            Если нужен метод по определенной выставке, то мотай ниже.
            """)
    @GetMapping("books")
    public PagedModel<ExhibitionBookResponse> get(@ParameterObject Pageable pageable){
        return exhibitionBookService.get(pageable);
    }
}
