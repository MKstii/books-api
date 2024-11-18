package com.utmn.books_api.domain.customer.repository;

import com.utmn.books_api.domain.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}