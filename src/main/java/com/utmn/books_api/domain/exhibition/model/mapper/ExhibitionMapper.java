package com.utmn.books_api.domain.exhibition.model.mapper;

import com.utmn.books_api.domain.book.model.mapper.BooksMapper;
import com.utmn.books_api.domain.book.model.response.BookResponse;
import com.utmn.books_api.domain.book.model.response.BookSearchResponse;
import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import com.utmn.books_api.domain.exhibition.model.entity.ExhibitionBook;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ExhibitionMapper {
    @Autowired
    BooksMapper booksMapper;

    @Mapping(target = "books", source = "books", qualifiedByName = "toBooks")
    public abstract ExhibitionResponse toResponse(Exhibition exhibition);

    @Named("toBooks")
    BookSearchResponse toBooks(ExhibitionBook exhibitionBook){
        return booksMapper.toSearchResponse(exhibitionBook.getBook());
    }
}
