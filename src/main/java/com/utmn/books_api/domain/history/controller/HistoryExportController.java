package com.utmn.books_api.domain.history.controller;

import com.utmn.books_api.domain.history.service.HistoryExcelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Tag(name = "History Export Controller")
@RestController
@RequestMapping("/history-export")
@RequiredArgsConstructor
public class HistoryExportController {

    private final HistoryExcelService historyExcelService;

    @GetMapping("/reminders")
    public ResponseEntity<ByteArrayResource> exportReminders() throws IOException {
        var book = historyExcelService.exportReminders();
        var stream = new ByteArrayOutputStream();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reminders.xlsx");
        book.write(stream);
        book.close();
        return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                header, HttpStatus.CREATED);
    }

    @GetMapping("/{bookId}/book-history")
    public ResponseEntity<ByteArrayResource> exportReminders(long bookId) throws IOException {
        var book = historyExcelService.exportBookHistory(bookId);
        var stream = new ByteArrayOutputStream();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=historyBooks.xlsx");
        book.write(stream);
        book.close();
        return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                header, HttpStatus.CREATED);
    }
}
