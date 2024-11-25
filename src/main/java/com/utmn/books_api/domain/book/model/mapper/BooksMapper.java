package com.utmn.books_api.domain.book.model.mapper;

import com.utmn.books_api.domain.author.model.entity.Author;
import com.utmn.books_api.domain.author.model.mapper.AuthorMapper;
import com.utmn.books_api.domain.author.model.response.AuthorShortResponse;
import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.book.model.entity.BookCover;
import com.utmn.books_api.domain.book.model.response.BookCoverResponse;
import com.utmn.books_api.domain.book.model.response.BookResponse;
import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BooksMapper {
    @Autowired
    AuthorMapper authorMapper;

    public abstract BookCoverResponse toResponse(BookCover entity);

    public abstract BookResponse toBookResponse(Book entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "authors", source = "authors", qualifiedByName = "toAuthorsResponse")
    public abstract BookSearchResponse toSearchResponse(Book entity);

    @Named("toAuthorsResponse")
    AuthorShortResponse toAuthorResponse(Author author){
        return authorMapper.toBookSearchResponse(author);
    }
}
