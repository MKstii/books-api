package com.utmn.books_api.book.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

//todo если гении аналитики придумают еще какие-то файлы хранить, то лучше сделать дженерик класс
@Getter
@Setter
@Entity(name = "file_model")
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("MIME тип")
    @Column(name = "mime_type")
    private String mimeType;

    @Comment("Путь в яндекс диске")
    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
