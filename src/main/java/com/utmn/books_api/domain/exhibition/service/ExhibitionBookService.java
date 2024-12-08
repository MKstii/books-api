package com.utmn.books_api.domain.exhibition.service;

import com.utmn.books_api.domain.exhibition.model.mapper.ExhibitionMapper;
import com.utmn.books_api.domain.exhibition.model.response.ExhibitionBookResponse;
import com.utmn.books_api.domain.exhibition.repository.ExhibitionBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class ExhibitionBookService {

    @Autowired
    private ExhibitionBookRepository exhibitionBookRepository;
    @Autowired
    private ExhibitionMapper exhibitionMapper;

    public PagedModel<ExhibitionBookResponse> get(Long id, Pageable pageable) {
        var entities = exhibitionBookRepository.findAllByExhibitionId(id, pageable);
        var response = entities.map(b -> exhibitionMapper.toResponse(b.getBook()));
        return new PagedModel<>(response);
    }

    public PagedModel<ExhibitionBookResponse> get(Pageable pageable) {
        var entities = exhibitionBookRepository.findAll(pageable);
        var response = entities.map(b -> exhibitionMapper.toResponse(b.getBook()));
        return new PagedModel<>(response);
    }
}
