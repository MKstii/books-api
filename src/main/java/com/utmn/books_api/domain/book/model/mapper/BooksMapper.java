package com.utmn.books_api.domain.book.model.mapper;

import com.utmn.books_api.domain.book.model.entity.BookCover;
import com.utmn.books_api.domain.book.model.response.BookCoverResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BooksMapper {

    BookCoverResponse toResponse(BookCover entity);
}