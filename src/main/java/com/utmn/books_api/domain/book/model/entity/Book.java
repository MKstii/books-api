package com.utmn.books_api.domain.book.model.entity;

import com.utmn.books_api.domain.author.Author;
import com.utmn.books_api.domain.history.History;
import com.utmn.books_api.domain.subjects.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
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

    @Column(name = "title", nullable = false)
    @Comment("")
    private String title;

    @Column(name = "subtitle", nullable = false)
    @Comment("")
    private String subtitle;

    @Column(name = "first_publish_date", nullable = false)
    @Comment("")
    private LocalDate firstPublishDate;

    @Column(name = "description")
    @Comment("")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "booksAuthors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @OneToOne
    @JoinColumn(name = "book_cover_id")
    private BookCover bookCover;

    @ManyToMany
    @JoinTable(name = "bookSubjects",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private List<Subject> subjects;

    @OneToMany(mappedBy = "book")
    private List<History> histories;
}
