package com.utmn.books_api.domain.book.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

//todo если гении аналитики придумают еще какие-то файлы хранить, то лучше сделать дженерик класс
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book_cover")
public class BookCover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("Путь в яндекс диске")
    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
