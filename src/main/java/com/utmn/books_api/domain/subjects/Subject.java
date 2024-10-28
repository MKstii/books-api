package com.utmn.books_api.domain.subjects;

import com.utmn.books_api.domain.book.model.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@Setter
@Comment("")
@Entity(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "subjectName", nullable = false)
    String name;

    @ManyToMany(mappedBy = "subjects")
    List<Book> books;
}
