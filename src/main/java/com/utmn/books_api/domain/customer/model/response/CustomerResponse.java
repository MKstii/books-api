package com.utmn.books_api.domain.customer.model.response;

import lombok.Data;

@Data
public class CustomerResponse {
    int id;
    String name;
    String address;
    String zip;
    String city;
    String email;
    String phone;
}
