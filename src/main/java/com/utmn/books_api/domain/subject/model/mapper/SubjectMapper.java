package com.utmn.books_api.domain.subject.model.mapper;

import com.utmn.books_api.domain.subject.model.entity.Subject;
import com.utmn.books_api.domain.subject.model.request.SubjectRequest;
import com.utmn.books_api.domain.subject.model.response.SubjectResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {

    Subject toEntity(SubjectRequest subjectRequest);

    SubjectResponse toResponse(Subject subject);

    void toEntity(SubjectRequest subjectResponse, @MappingTarget Subject subject);
}