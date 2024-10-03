package com.utmn.books_api.book.mapper;

import com.utmn.books_api.book.model.entity.FileModel;
import com.utmn.books_api.book.model.view.FileModelView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BooksMapper {

    BooksMapper INSTANCE = Mappers.getMapper(BooksMapper.class);

    FileModelView from(FileModel entity);
}
