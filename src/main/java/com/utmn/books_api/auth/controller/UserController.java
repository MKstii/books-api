package com.utmn.books_api.auth.controller;

import com.utmn.books_api.auth.model.entity.AppUser;
import com.utmn.books_api.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    @Secured("ADMIN")
    public List<AppUser> get() {
        return userRepository.findAll();
    }
}
