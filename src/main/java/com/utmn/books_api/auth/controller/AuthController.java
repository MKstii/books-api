package com.utmn.books_api.auth.controller;

import com.utmn.books_api.auth.model.entity.AppUser;
import com.utmn.books_api.auth.service.AuthService;
import com.utmn.books_api.auth.model.request.LoginRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
@Tag(name = "Authorization")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<AppUser> register(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(CREATED)
                .body(this.authService.register(loginRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AppUser> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity
                .status(OK)
                .body(authService.login(loginRequest, request, response));
    }

    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("Метод не будет вызываться, чисто только для swagger-ui");
    }

    @GetMapping("/check-you-is-live")
    public boolean checkIsLive(HttpServletRequest request) {
        return authService.check(request);
    }
}