package com.utmn.books_api.auth.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Manager extends AppUser {

    private String fio;
}
