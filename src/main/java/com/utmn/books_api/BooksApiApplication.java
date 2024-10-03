package com.utmn.books_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BooksApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksApiApplication.class, args);
    }

}
