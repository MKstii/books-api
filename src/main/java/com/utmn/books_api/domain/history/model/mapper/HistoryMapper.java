package com.utmn.books_api.domain.history.model.mapper;

import com.utmn.books_api.domain.history.model.entity.History;
import com.utmn.books_api.domain.history.model.request.HistoryRequest;
import com.utmn.books_api.domain.history.model.response.HistoryRemindersResponse;
import com.utmn.books_api.domain.history.model.response.HistoryResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface HistoryMapper {

    @Mapping(target = "book.id", source = "booksId")
    History toEntity(HistoryRequest request);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    HistoryResponse toResponse(History entity);

    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "title", source = "book.title")
    HistoryRemindersResponse toRemindersResponse(History entity);

    @Mapping(target = "book.id", source = "booksId")
    void toEntity(HistoryRequest request, @MappingTarget History entity);
}