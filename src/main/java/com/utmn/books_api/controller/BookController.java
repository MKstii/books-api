package com.utmn.books_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("get")
    public String get(){
        return "aaa";
    }
}
