package com.utmn.books_api.auth.model.view;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
