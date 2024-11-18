package com.utmn.books_api.domain.customer.service;

import com.utmn.books_api.domain.customer.model.entity.Customer;
import com.utmn.books_api.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getEntity(int id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Клиент с идентификатором `%s` не найден".formatted(id)));
    }
}
