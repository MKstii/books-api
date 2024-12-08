package com.utmn.books_api.domain.exhibition.model.mapper;

import com.utmn.books_api.domain.author.model.entity.Author;
import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.book.model.mapper.BooksMapper;
import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import com.utmn.books_api.domain.exhibition.model.entity.ExhibitionBook;
import com.utmn.books_api.domain.exhibition.model.request.ExhibitionRequest;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionBookResponse;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import com.utmn.books_api.domain.subject.model.entity.Subject;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExhibitionMapper {

    @Mapping(target = "books", source = "books", qualifiedByName = "toBookSearchResponse")
    ExhibitionResponse toResponse(Exhibition exhibition);

    Exhibition toEntity(ExhibitionRequest request);

    @AfterMapping
    default void setSelf(@MappingTarget Exhibition entity) {
        entity.getBooks().forEach(c -> c.setExhibition(entity));
    }

    @Named("toBookSearchResponse")
    default BookSearchResponse toBookSearchResponse(ExhibitionBook exhibitionBooks) {
        return Mappers
                .getMapper(BooksMapper.class)
                .toSearchResponse(exhibitionBooks.getBook());
    }

    void toEntity(@MappingTarget Exhibition entity, ExhibitionRequest request);

    @Mapping(target = "authors", source = "authors", qualifiedByName = "toOneStringAuthors")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "toOneStringSubjects")
    ExhibitionBookResponse toResponse(Book entity);

    @Named("toOneStringAuthors")
    default String toOneStringAuthors(List<Author> entities) {
        return entities.stream().map(Author::getName).collect(Collectors.joining(", "));
    }

    @Named("toOneStringSubjects")
    default String toOneStringSubjects(List<Subject> entities) {
        return entities.stream().map(Subject::getName).collect(Collectors.joining(", "));
    }
}
