package com.utmn.books_api.domain.book.model.entity;

import com.utmn.books_api.domain.author.model.entity.Author;
import com.utmn.books_api.domain.history.model.entity.History;
import com.utmn.books_api.domain.subject.model.entity.Subject;
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
    @Comment("Наименование")
    private String title;

    @Column(name = "subtitle", nullable = false)
    @Comment("Подзаголовок")
    private String subtitle;

    @Column(name = "first_publish_date", nullable = false)
    @Comment("Дата публикации")
    private LocalDate firstPublishDate;

    @Column(name = "description")
    @Comment("Описание")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookCover> covers;

    @ManyToMany
    @JoinTable(name = "books_subjects",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @OneToMany(mappedBy = "book")
    private List<History> histories;
}
