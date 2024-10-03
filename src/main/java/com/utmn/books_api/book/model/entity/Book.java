package com.utmn.books_api.book.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

//todo если не будет лень, то javadoc комменты лучше ставить
@Getter
@Setter
@Entity(name = "book")
@Comment("Книга")//хз, стоит, все равно никто не полезет в базу
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", orphanRemoval = true)
    private List<FileModel> files;
}
