package com.utmn.books_api.domain.subject.service;

import com.utmn.books_api.domain.subject.model.entity.Subject;
import com.utmn.books_api.domain.subject.model.mapper.SubjectMapper;
import com.utmn.books_api.domain.subject.model.request.SubjectRequest;
import com.utmn.books_api.domain.subject.model.response.SubjectResponse;
import com.utmn.books_api.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository SubjectRepository;
    private final SubjectMapper mapper;

    public Page<SubjectResponse> getList(Pageable pageable) {
        var entities = SubjectRepository.findAll(pageable);
        return entities.map(mapper::toResponse);
    }

    public Subject getEntity(int id) {
        Optional<Subject> SubjectOptional = SubjectRepository.findById(id);
        return SubjectOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Жанр с идентификатором id `%s` не найден".formatted(id))
        );
    }

    public SubjectResponse getOne(int id) {
        var response = getEntity(id);
        return mapper.toResponse(response);
    }

    public SubjectResponse create(SubjectRequest request) {
        var entity = mapper.toEntity(request);
        entity = SubjectRepository.save(entity);
        return mapper.toResponse(entity);
    }

    public SubjectResponse create(int id, SubjectRequest request) {
        var entity = getEntity(id);
        mapper.toEntity(request, entity);
        SubjectRepository.save(entity);
        return mapper.toResponse(entity);
    }

    public SubjectResponse delete(int id) {
        var entity = getEntity(id);
        SubjectRepository.delete(entity);
        return mapper.toResponse(entity);
    }
}
