package com.utmn.books_api.domain.history.controller;

import com.utmn.books_api.domain.history.model.response.HistoryRemindersResponse;
import com.utmn.books_api.domain.history.service.HistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@Tag(name = "History Controller")
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService service;

    //todo мейби что-то лучше придумаем хз
    @GetMapping("/reminders")
    public PagedModel<HistoryRemindersResponse> get(@ParameterObject Pageable pageable) {
        return service.reminders(pageable);
    }

    @GetMapping("{bookId}/book-history")
    public PagedModel<HistoryRemindersResponse> getBookHistory(@PathVariable(name = "bookId") long bookId,
                                                      @ParameterObject Pageable pageable) {
        return service.bookHistory(bookId, pageable);
    }
}
