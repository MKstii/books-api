package com.utmn.books_api.domain.subject.controller;

import com.utmn.books_api.domain.subject.model.request.SubjectRequest;
import com.utmn.books_api.domain.subject.model.response.SubjectResponse;
import com.utmn.books_api.domain.subject.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Subject Controller")
@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@Validated
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public PagedModel<SubjectResponse> getList(@ParameterObject Pageable pageable) {
        var Subjects = subjectService.getList(pageable);
        return new PagedModel<>(Subjects);
    }

    @GetMapping("/{id}")
    public SubjectResponse getOne(@PathVariable int id) {
        return subjectService.getOne(id);
    }

    @PostMapping
    public SubjectResponse create(@RequestBody @Valid SubjectRequest Subject) {
        return subjectService.create(Subject);
    }

    @PutMapping("/{id}")
    public SubjectResponse update(@PathVariable int id, @RequestBody @Valid SubjectRequest request) {
        return subjectService.create(id, request);
    }


    @DeleteMapping("/{id}")
    public SubjectResponse delete(@PathVariable int id) {
        return subjectService.delete(id);
    }
}
