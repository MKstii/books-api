package com.utmn.books_api.domain.history.controller;

import com.utmn.books_api.domain.history.model.request.HistoryRequest;
import com.utmn.books_api.domain.history.model.response.HistoryResponse;
import com.utmn.books_api.domain.history.service.HistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "History Controller")
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryClientController {

    private final HistoryService service;

    @GetMapping
    public PagedModel<HistoryResponse> get(@ParameterObject Pageable pageable) {
        return service.get(pageable);
    }

    //    @GetMapping("/{id}")
//    public HistoryResponse getOne(@PathVariable Long id) {
////        return authorService.getOne(id);
//    }
//
    @PostMapping("/make-issue/{customerId}/{bookId}")
    public HistoryResponse create(@PathVariable int customerId, @PathVariable long bookId) {
        return service.create(customerId, bookId);
    }

    @PutMapping("/make-return/{id}")
    public HistoryResponse update(@PathVariable long id) {
        return service.update(id);
    }


//    @DeleteMapping("/{id}")
//    public HistoryResponse delete(@PathVariable Long id) {
////        return authorService.delete(id);
//    }

    @GetMapping("/{customerId}/current_issue")
    public PagedModel<HistoryResponse> currentIssue(
            @PathVariable int customerId,
            @ParameterObject Pageable pageable) {
        return service.currentIssue(customerId, pageable);
    }

    @GetMapping("/customerHistory/{customerId}")
    public PagedModel<HistoryResponse> customerHistory(
            @ParameterObject Pageable pageable,
            @PathVariable int customerId) {
        return service.customerHistory(customerId, pageable);
    }
}
