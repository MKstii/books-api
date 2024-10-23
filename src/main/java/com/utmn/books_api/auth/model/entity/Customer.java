package com.utmn.books_api.auth.model.entity;

import com.utmn.books_api.domain.history.History;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Customer extends AppUser{

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "zip")
    private String zip;

    @OneToMany(mappedBy = "customer")
    private List<History> histories;
}
