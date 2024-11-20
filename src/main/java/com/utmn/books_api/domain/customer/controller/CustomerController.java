package com.utmn.books_api.domain.customer.controller;

import com.utmn.books_api.domain.author.model.request.AuthorRequest;
import com.utmn.books_api.domain.author.model.response.AuthorResponse;
import com.utmn.books_api.domain.customer.model.request.CustomerRequest;
import com.utmn.books_api.domain.customer.model.response.CustomerResponse;
import com.utmn.books_api.domain.customer.repository.CustomerRepository;
import com.utmn.books_api.domain.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer Controller")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public PagedModel<CustomerResponse> getCustomers(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name= "name", required = false) String name,
            @ParameterObject Pageable pageable){
        var customers = customerService.getList(pageable, name, id);
        return new PagedModel<>(customers);
    }

    @PostMapping
    public CustomerResponse create(@RequestBody CustomerRequest customer) {
        return customerService.create(customer);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Integer id, @RequestBody CustomerRequest customer){
        return customerService.update(id, customer);
    }
}
