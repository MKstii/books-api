package com.utmn.books_api.domain.customer.service;

import com.utmn.books_api.domain.customer.model.entity.Customer;
import com.utmn.books_api.domain.customer.model.mapper.CustomerMapper;
import com.utmn.books_api.domain.customer.model.request.CustomerRequest;
import com.utmn.books_api.domain.customer.model.response.CustomerResponse;
import com.utmn.books_api.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer getEntity(int id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Клиент с идентификатором `%s` не найден".formatted(id)));
    }

    public Page<CustomerResponse> getList(Pageable pageable, String name, Integer id){
        Page<Customer> entities;
        if(id != null){
            entities = customerRepository.findById(pageable, id);
        }
        else if(name != null){
            entities = customerRepository.findByNameIgnoreCaseContaining(pageable, name);
        }
        else {
            entities = customerRepository.findAll(pageable);
        }
        return entities.map(customerMapper::toResponse);
    }

    public CustomerResponse create(CustomerRequest customer){
        var entity = customerMapper.toEntity(customer);
        var savedEntity = customerRepository.save(entity);
        return customerMapper.toResponse(savedEntity);
    }

    public CustomerResponse update(Integer id, CustomerRequest customer){
        var entity = getEntity(id);
        customerMapper.toEntity(customer, entity);
        customerRepository.save(entity);
        return customerMapper.toResponse(entity);
    }
}
