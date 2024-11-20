package com.utmn.books_api.domain.customer.repository;

import com.utmn.books_api.domain.customer.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Page<Customer> findById(Pageable pageable, Integer id);
    public Page<Customer> findByNameIgnoreCaseContaining(Pageable pageable, String name);

}