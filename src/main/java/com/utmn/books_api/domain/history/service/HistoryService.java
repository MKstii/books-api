package com.utmn.books_api.domain.history.service;

import com.utmn.books_api.domain.customer.service.CustomerService;
import com.utmn.books_api.domain.history.model.entity.History;
import com.utmn.books_api.domain.history.model.mapper.HistoryMapper;
import com.utmn.books_api.domain.history.model.request.HistoryRequest;
import com.utmn.books_api.domain.history.model.response.HistoryRemindersResponse;
import com.utmn.books_api.domain.history.model.response.HistoryResponse;
import com.utmn.books_api.domain.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository repository;
    private final HistoryMapper mapper;

    private final CustomerService customerService;

    public PagedModel<HistoryResponse> get(Pageable pageable) {
        var entities = repository.findAll(pageable);
        return new PagedModel<>(entities.map(mapper::toResponse));
    }

    public History get(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "История с идентификатором `%s` не найден".formatted(id)));
    }

    public HistoryResponse create(int customerId, HistoryRequest request) {
        var entity = mapper.toEntity(request);
        if (entity.getDateOfIssue() == null) {
            entity.setDateOfIssue(LocalDate.now());
        }
        if (entity.getReturnDate() == null) {
            entity.setReturnDate(LocalDate.now().plusDays(21));
        }
        var customer = customerService.getEntity(customerId);
        entity.setCustomer(customer);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public HistoryResponse update(long id, LocalDate returnDueDate) {
        var entity = get(id);
        entity.setReturnDueDate(returnDueDate);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public PagedModel<HistoryResponse> currentIssue(int customerId, Pageable pageable) {
        var entities = repository.findAllByCustomerIdWithoutHistorical(customerId, pageable);
        return new PagedModel<>(entities.map(mapper::toResponse));
    }

    public PagedModel<HistoryResponse> customerHistory(int customerId, Pageable pageable) {
        var entities = repository.findAllByCustomerId(customerId, pageable);
        return new PagedModel<>(entities.map(mapper::toResponse));
    }

    public PagedModel<HistoryRemindersResponse> reminders(Pageable pageable) {
        var entities = repository.findReminders(pageable);
        return new PagedModel<>(entities.map(mapper::toRemindersResponse));
    }
}
