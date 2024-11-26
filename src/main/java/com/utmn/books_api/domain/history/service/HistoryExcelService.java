package com.utmn.books_api.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class HistoryExcelService {

    private final HistoryService service;

    //todo ширина колонок
    public Workbook exportReminders() {
        var items = service.reminders(Pageable.unpaged());

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Задержанные книги");
        var rowIndex = 0;
        var row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue("№");
        row.createCell(1).setCellValue("Название книги");
        row.createCell(2).setCellValue("Имя клиента");
        row.createCell(3).setCellValue("Дата выдачи книги");
        row.createCell(3).setCellValue("Дата планируемой сдачи книги");

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (var item : items.getContent()) {
            row = sheet.createRow(++rowIndex);
            row.createCell(0).setCellValue(item.getId());
            row.createCell(1).setCellValue(item.getTitle());
            row.createCell(2).setCellValue(item.getCustomerName());
            row.createCell(3).setCellValue(item.getDateOfIssue());
            row.createCell(3).setCellValue(item.getReturnDate().format(formatter));
        }
        return book;
    }

    public Workbook exportBookHistory(long bookId) {
        var items = service.bookHistory(bookId, Pageable.unpaged());

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Задержанные книги");
        var rowIndex = 0;
        var row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue("№");
        row.createCell(1).setCellValue("Название книги");
        row.createCell(2).setCellValue("Имя клиента");
        row.createCell(3).setCellValue("Дата выдачи книги");
        row.createCell(3).setCellValue("Дата планируемой сдачи книги");

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (var item : items.getContent()) {
            row = sheet.createRow(++rowIndex);
            row.createCell(0).setCellValue(item.getId());
            row.createCell(1).setCellValue(item.getTitle());
            row.createCell(2).setCellValue(item.getCustomerName());
            row.createCell(3).setCellValue(item.getDateOfIssue());
            row.createCell(3).setCellValue(item.getReturnDate().format(formatter));
        }
        return book;
    }
}
