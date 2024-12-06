package com.utmn.books_api.domain.exhibition.controller;

import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import com.utmn.books_api.domain.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/exhibition")
@RequiredArgsConstructor
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @GetMapping("/{id}")
    public ExhibitionResponse getExhibitionById(@PathVariable Long id){
        return exhibitionService.getById(id);
    }
}
