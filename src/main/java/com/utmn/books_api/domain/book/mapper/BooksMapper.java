package com.utmn.books_api.domain.book.mapper;

import com.utmn.books_api.domain.book.model.entity.FileModel;
import com.utmn.books_api.domain.book.model.view.FileModelView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BooksMapper {

    BooksMapper INSTANCE = Mappers.getMapper(BooksMapper.class);

    FileModelView from(FileModel entity);
}
