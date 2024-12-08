package com.utmn.books_api.domain.exhibition.service;

import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import com.utmn.books_api.domain.exhibition.model.mapper.ExhibitionMapper;
import com.utmn.books_api.domain.exhibition.model.request.ExhibitionRequest;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import com.utmn.books_api.domain.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    //Такой хрени еще не приходилось делать
    public PagedModel<ExhibitionResponse> get(String name, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (name == null && startDate == null && endDate == null) {
            var entities = exhibitionRepository.findAll(pageable);
            return new PagedModel<>(entities.map(exhibitionMapper::toResponse));
        } else {
            LocalDateTime startStartDay = null;
            LocalDateTime startEndDay = null;
            LocalDateTime endStartDay = null;
            LocalDateTime endEndDay = null;
            if (startDate != null) {
                startStartDay = startDate.atStartOfDay();
                startEndDay = startDate.atTime(23, 59, 59);
            } else if (endDate != null) {
                endStartDay = endDate.atStartOfDay();
                endEndDay = endDate.atTime(23, 59, 59);
            }
            var entities = exhibitionRepository.getAllByFilters(
                    name, startStartDay, startEndDay, endStartDay, endEndDay, pageable
            );
            return new PagedModel<>(entities.map(exhibitionMapper::toResponse));
        }
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

    public void delete(Long id){
        exhibitionRepository.deleteById(id);
    }
}
