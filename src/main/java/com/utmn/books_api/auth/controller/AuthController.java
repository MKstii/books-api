package com.utmn.books_api.auth.controller;

import com.utmn.books_api.auth.service.AuthService;
import com.utmn.books_api.auth.model.view.LoginRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(CREATED)
                .body(this.authService.register(loginRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity
                .status(OK)
                .body(authService.login(loginRequest, request, response));
    }
}
