package com.utmn.books_api.domain.author.model.mapper;

import com.utmn.books_api.domain.author.model.entity.Author;
import com.utmn.books_api.domain.author.model.request.AuthorRequest;
import com.utmn.books_api.domain.author.model.response.AuthorResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AuthorMapper {


    Author toEntity(AuthorRequest request);

    AuthorResponse toResponse(Author entity);

    void toEntity(AuthorRequest request, @MappingTarget Author entity);
}