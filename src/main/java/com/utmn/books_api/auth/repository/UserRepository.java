package com.utmn.books_api.auth.repository;


import com.utmn.books_api.auth.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByLogin(String login);
}
