package com.utmn.books_api.domain.history.service;

import com.utmn.books_api.domain.book.service.BookService;
import com.utmn.books_api.domain.customer.service.CustomerService;
import com.utmn.books_api.domain.history.model.entity.History;
import com.utmn.books_api.domain.history.model.mapper.HistoryMapper;
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
    private final BookService bookService;

    public PagedModel<HistoryResponse> get(Pageable pageable) {
        var entities = repository.findAll(pageable);
        return new PagedModel<>(entities.map(mapper::toResponse));
    }

    public History get(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "История с идентификатором `%s` не найден".formatted(id)));
    }

    public HistoryResponse create(int customerId, long bookId) {
        int issueCount = repository.issueCountByCustomerId(customerId);

        // todo Дима не может обработать нормально ошибку?
        // К чему такая дичь
        if(issueCount >= 5){
            return null;
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Слишком много книг, умник");
        }

        var history = new History();
        var customer = customerService.getEntity(customerId);
        var book = bookService.getEntity(bookId);
        history.setCustomer(customer);
        history.setBook(book);
        history.setDateOfIssue(LocalDate.now());
        history.setReturnDueDate(LocalDate.now().plusDays(21));
        repository.save(history);
        return mapper.toResponse(history);
    }

    public HistoryResponse update(long historyId) {
        var entity = get(historyId);
        entity.setReturnDate(LocalDate.now());
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public PagedModel<HistoryResponse> currentIssue(int customerId, Pageable pageable) {
        var entities = repository.findAllByCustomerIdWithoutHistorical(customerId, pageable);
        return new PagedModel<>(entities.map(mapper::toResponse));
    }

    public PagedModel<HistoryResponse> customerHistory(int customerId, Pageable pageable) {
        var entities = repository.findAllByCustomerIdAndReturnDateNotNull(customerId, pageable);
        return new PagedModel<>(entities.map(mapper::toResponse));
    }

    public PagedModel<HistoryRemindersResponse> reminders(Pageable pageable) {
        var entities = repository.findReminders(pageable);
        return new PagedModel<>(entities.map(mapper::toRemindersResponse));
    }

    public PagedModel<HistoryRemindersResponse> bookHistory(long bookId, Pageable pageable) {
        var entities = repository.findByBookId(bookId, pageable);
        return new PagedModel<>(entities.map(mapper::toRemindersResponse));
    }
}
