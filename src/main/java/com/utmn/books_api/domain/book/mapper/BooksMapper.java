package com.utmn.books_api.domain.book.mapper;

import com.utmn.books_api.domain.book.model.entity.BookCover;
import com.utmn.books_api.domain.book.model.view.BookCoverResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BooksMapper {

    BookCoverResponse toResponse(BookCover entity);
}
