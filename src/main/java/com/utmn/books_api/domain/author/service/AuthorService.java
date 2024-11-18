package com.utmn.books_api.domain.author.service;

import com.utmn.books_api.domain.author.model.entity.Author;
import com.utmn.books_api.domain.author.model.mapper.AuthorMapper;
import com.utmn.books_api.domain.author.model.request.AuthorRequest;
import com.utmn.books_api.domain.author.model.response.AuthorResponse;
import com.utmn.books_api.domain.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    public Page<AuthorResponse> getList(Pageable pageable) {
        var entities = authorRepository.findAll(pageable);
        return entities.map(mapper::toResponse);
    }

    public Author getEntity(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Автор с идентификатором id `%s` не найден".formatted(id)));
    }

    public AuthorResponse getOne(Long id) {
        var response = getEntity(id);
        return mapper.toResponse(response);
    }

    public AuthorResponse create(AuthorRequest request) {
        var entity = mapper.toEntity(request);
        entity = authorRepository.save(entity);
        return mapper.toResponse(entity);
    }

    public AuthorResponse update(Long id, AuthorRequest request) {
        var entity = getEntity(id);
        mapper.toEntity(request, entity);
        authorRepository.save(entity);
        return mapper.toResponse(entity);
    }

    public AuthorResponse delete(Long id) {
        var entity = getEntity(id);
        authorRepository.delete(entity);
        return mapper.toResponse(entity);
    }
}
