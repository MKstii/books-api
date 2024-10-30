package com.utmn.books_api.domain.book.controller;

import com.utmn.books_api.domain.book.model.view.BookCoverResponse;
import com.utmn.books_api.domain.book.service.BookFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@Tag(name = "Books Files")
@RequestMapping("/books/{id}/files")
@RequiredArgsConstructor
public class BookFileController {

    private final BookFileService bookFileService;

    @GetMapping
    @Operation(description = "Возващает список файлов книги")
    public List<BookCoverResponse> get(@PathVariable(name = "id") long id) {
        return bookFileService.get(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@PathVariable(name = "id") long id, @RequestBody MultipartFile file) {
        return bookFileService.upload(id, file);
    }
}
