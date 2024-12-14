package com.utmn.books_api.domain.exhibition.service;

import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import com.utmn.books_api.domain.exhibition.model.mapper.ExhibitionMapper;
import com.utmn.books_api.domain.exhibition.model.request.ExhibitionRequest;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import com.utmn.books_api.domain.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    protected final ExhibitionMapper exhibitionMapper;

    public Exhibition getEntity(Long id) {
        return exhibitionRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Клиент с идентификатором `%s` не найден".formatted(id)));
    }

    public ExhibitionResponse getById(Long id) {
        return exhibitionMapper.toResponse(getEntity(id));
    }

    public PagedModel<ExhibitionResponse> get(String name, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<Exhibition> entities;
        if (name != null) {
            entities = exhibitionRepository.findAllByName(name, pageable);
        } else if (startDate != null && endDate != null) {
            entities = exhibitionRepository.findAllByStartDateBetween(startDate, endDate, pageable);
        } else if (startDate != null) {
            entities = exhibitionRepository.findAllByStartDateGreaterThanEqual(startDate, pageable);
        } else {
            entities = exhibitionRepository.findAll(pageable);
        }
        return new PagedModel<>(entities.map(exhibitionMapper::toResponse));
    }

    public ExhibitionResponse create(ExhibitionRequest request) {
        var entity = exhibitionMapper.toEntity(request);
        exhibitionRepository.save(entity);
        return exhibitionMapper.toResponse(entity);
    }

    public ExhibitionResponse update(Long id, ExhibitionRequest request) {
        var entity = getEntity(id);
        exhibitionMapper.toEntity(entity, request);
        exhibitionRepository.save(entity);
        return exhibitionMapper.toResponse(entity);
    }

    public void delete(Long id) {
        exhibitionRepository.deleteById(id);
    }
}
