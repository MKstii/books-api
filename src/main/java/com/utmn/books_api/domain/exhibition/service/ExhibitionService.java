package com.utmn.books_api.domain.exhibition.service;

import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.exhibition.model.entity.Exhibition;
import com.utmn.books_api.domain.exhibition.model.mapper.ExhibitionMapper;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionResponse;
import com.utmn.books_api.domain.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionMapper exhibitionMapper;

    public Exhibition getEntity(Long id) {
        return exhibitionRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Клиент с идентификатором `%s` не найден".formatted(id)));
    }

    public ExhibitionResponse getById(Long id){
        var entity = exhibitionRepository.findById(id);
        return exhibitionMapper.toResponse(entity.get());
    }
}
