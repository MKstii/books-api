package com.utmn.books_api.auth.model.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {

    ADMIN("admin"),
    PUBLIC("user");

    private final String name;
}
