package com.utmn.books_api.domain.book.service;

import com.utmn.books_api.domain.book.model.mapper.BooksMapper;
import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.book.model.entity.BookCover;
import com.utmn.books_api.domain.book.model.dto.YandexApiResponseFileUpload;
import com.utmn.books_api.domain.book.model.dto.YandexApiResponseError;
import com.utmn.books_api.domain.book.model.response.BookCoverResponse;
import com.utmn.books_api.domain.book.repository.BookCoverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookFileService {

    @Value("${yandex-disk.token}")
    private String token;

    @Value("${yandex-disk.url}")
    private String url;

    @Value("${yandex-disk.dir}")
    private String directory;

    private static final String PATH_UPLOAD = "upload";
    private static final String PATH_DOWNLOAD = "download";

    private final BookCoverRepository bookCoverRepository;
    //private final BooksMapper mapper;

    public List<BookCoverResponse> get(long id) {
        var entities = bookCoverRepository.findByBookId(id);
        return new ArrayList<BookCoverResponse>();
    }

    /**
     * Заггрузка файла
     *
     * @param id   Идентификатор файла
     * @param file Файл
     * @return Ссылка для скачивания файла
     */
    public String upload(long id, MultipartFile file) {
        //todo стоит изменить принцип генерции названий для файлов
        var currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss"));
        var filePath = "/" + directory + "/" + currentDateTime;
        //https://cloud-api.yandex.net/v1/disk/resources/
        var uri = UriComponentsBuilder
                .fromUriString(url)
                .path(PATH_UPLOAD)
                .queryParam("path", filePath)
                .build()
                .toUriString();

        var result = WebClient.create(uri)
                .get()
                .header("Authorization", "OAuth " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (response) -> {
                            var error = response.bodyToMono(YandexApiResponseError.class).block();
                            throw new ResponseStatusException(response.statusCode(), error.message());
                        }
                )
                .bodyToMono(YandexApiResponseFileUpload.class)
                .block();

        var uploadUrl = result.href();
        uploadFile(uploadUrl, file);

        var fileId = create(id, file, filePath);
        log.info("Загружен файл id=%d, filePath=%s".formatted(fileId, filePath));

        return getDownloadPath(filePath);
    }

    /**
     * Создаем модельку в базе
     *
     * @param id       Идентификатор книги
     * @param file     Файл
     * @param filePath Путь к файлу
     */
    private long create(long id, MultipartFile file, String filePath) {

        var book = new Book();
        book.setId(id);

        var entityFile = BookCover.builder()
                .path(filePath)
                .book(book)
                .build();

        return bookCoverRepository.save(entityFile).getId();
    }

    /**
     * Загружает файл
     *
     * @param uploadUrl Ссылка директории и название файла
     * @param file      Файл
     */
    private void uploadFile(String uploadUrl, MultipartFile file) {
        try {
            //todo переделать под WebClient
            //хз почему ломается
            RestClient.create()
                    .put()
                    .uri(uploadUrl)
                    .body(file.getBytes())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new ResponseStatusException(response.getStatusCode(), response.getStatusText());
                    });
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Возвращает путь для скачивания файла
     *
     * @param filePath путь хранения
     * @return Ссылка для скачивания
     */
    public String getDownloadPath(String filePath) {
        var uri = UriComponentsBuilder
                .fromUriString(url)
                .path(PATH_DOWNLOAD)
                .queryParam("path", filePath)
                .build()
                .toUriString();

        var result = WebClient.create(uri)
                .get()
                .header("Authorization", "OAuth " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (response) -> {
                    var error = response.bodyToMono(YandexApiResponseError.class).block();
                    throw new ResponseStatusException(response.statusCode(), error.message());
                })
                .bodyToMono(YandexApiResponseFileUpload.class)
                .block();
        return result.href();
    }
}
