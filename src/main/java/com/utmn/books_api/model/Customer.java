package com.utmn.books_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String zip;

    @OneToMany(mappedBy = "customer")
    private List<History> histories;
}

