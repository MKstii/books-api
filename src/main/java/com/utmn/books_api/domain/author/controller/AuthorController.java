package com.utmn.books_api.domain.author.controller;

import com.utmn.books_api.domain.author.model.request.AuthorRequest;
import com.utmn.books_api.domain.author.model.response.AuthorResponse;
import com.utmn.books_api.domain.author.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Author Controller")
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public PagedModel<AuthorResponse> getList(@ParameterObject Pageable pageable) {
        var authors = authorService.getList(pageable);
        return new PagedModel<>(authors);
    }

    @GetMapping("/{id}")
    public AuthorResponse getOne(@PathVariable Long id) {
        return authorService.getOne(id);
    }

    @PostMapping
    public AuthorResponse create(@RequestBody AuthorRequest author) {
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable Long id, @RequestBody AuthorRequest request) {
        return authorService.update(id, request);
    }


    @DeleteMapping("/{id}")
    public AuthorResponse delete(@PathVariable Long id) {
        return authorService.delete(id);
    }
}
