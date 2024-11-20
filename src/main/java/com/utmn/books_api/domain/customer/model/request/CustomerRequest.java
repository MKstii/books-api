package com.utmn.books_api.domain.customer.model.request;

import lombok.Data;

@Data
public class CustomerRequest {
    String name;
    String address;
    String zip;
    String city;
    String email;
    String phone;
}
