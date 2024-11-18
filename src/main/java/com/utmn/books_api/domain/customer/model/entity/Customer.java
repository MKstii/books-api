package com.utmn.books_api.domain.customer.model.entity;

import com.utmn.books_api.domain.history.model.entity.History;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@Setter
@Entity(name = "customer")
@Comment("Клиент")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Идентификатор")
    private Integer id;

    @Column(name = "name")
    @Comment("ФИО")
    private String name;

    @Column(name = "address")
    @Comment("Адрес")
    private String address;

    @Column(name = "city")
    @Comment("Город")
    private String city;

    @Column(name = "phone")
    @Comment("Телефон")
    private String phone;

    @Column(name = "email")
    @Comment("Почта")
    private String email;

    @Column(name = "zip")
    @Comment("Почтовый код")
    private String zip;

    @OneToMany(mappedBy = "customer")
    private List<History> histories;
}
