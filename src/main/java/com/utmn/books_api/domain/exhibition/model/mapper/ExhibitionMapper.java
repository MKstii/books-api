package com.utmn.books_api.domain.exhibition.model.mapper;

import com.utmn.books_api.domain.book.model.mapper.BooksMapper;
import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import com.utmn.books_api.domain.exhibition.model.entity.ExhibitionBook;
import com.utmn.books_api.domain.exhibition.model.request.ExhibitionRequest;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

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
}
