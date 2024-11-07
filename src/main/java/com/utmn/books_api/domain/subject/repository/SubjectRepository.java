package com.utmn.books_api.domain.subject.repository;

import com.utmn.books_api.domain.subject.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}