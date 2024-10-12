package com.utmn.books_api.domain.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utmn.books_api.domain.book.mapper.BooksMapper;
import com.utmn.books_api.domain.book.model.entity.Book;
import com.utmn.books_api.domain.book.model.entity.FileModel;
import com.utmn.books_api.domain.book.model.dto.YandexApiResponseFileUpload;
import com.utmn.books_api.domain.book.model.dto.YandexApiResponseError;
import com.utmn.books_api.domain.book.model.view.FileModelView;
import com.utmn.books_api.domain.book.repository.FileModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
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
    private final ObjectMapper objectMapper;

    private final FileModelRepository fileModelRepository;
    private final BooksMapper mapper = BooksMapper.INSTANCE;

    public List<FileModelView> get(long id) {
        var entities = fileModelRepository.findByBookId(id);
        return entities.stream().map(mapper::from).toList();
    }

    /**
     * Заггрузка файла
     *
     * @param id   Идентификатор файла
     * @param file Файл
     * @return Ссылка для скачивания файла
     */
    public String upload(long id, MultipartFile file) {
        var restClient = RestClient.create();

        //todo стоит изменить принцип генерции названий для файлов
        var currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss"));
        var filePath = "/" + directory + "/" + currentDateTime;
        var uri = UriComponentsBuilder
                .fromUriString(url)
                .path(PATH_UPLOAD)
                .queryParam("path", filePath)
                .build()
                .toUriString();
        var result = restClient.get()
                .uri(uri)
                .header("Authorization", "OAuth " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, response) -> {
                    var error = objectMapper.convertValue(response.getBody(), YandexApiResponseError.class);
                    throw new ResponseStatusException(response.getStatusCode(), error.message());
                })
                .body(YandexApiResponseFileUpload.class);

        var uploadUrl = result.href();
        uploadFile(uploadUrl, file);

        create(id, file, filePath);

        return getDownloadPath(filePath);
    }

    /**
     * Создаем модельку в базе
     *
     * @param id       Идентификатор книги
     * @param file     Файл
     * @param filePath Путь к файлу
     */
    private void create(long id, MultipartFile file, String filePath) {
        var entityFile = new FileModel();
        var book = new Book();
        book.setId(id);

        entityFile.setPath(filePath);
        entityFile.setMimeType(file.getContentType());
        entityFile.setBook(book);
        fileModelRepository.save(entityFile);
    }

    /**
     * Загружает файл
     *
     * @param uploadUrl Ссылка директории и название файла
     * @param file      Файл
     */
    private void uploadFile(String uploadUrl, MultipartFile file) {
        try {
            var restClient = RestClient.create();
            restClient.put()
                    .uri(uploadUrl)
                    .body(file.getBytes())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (_, response) -> {
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
    private String getDownloadPath(String filePath) {
        var restClient = RestClient.create();
        var uri = UriComponentsBuilder
                .fromUriString(url)
                .path(PATH_DOWNLOAD)
                .queryParam("path", filePath)
                .build()
                .toUriString();
        var result = restClient.get()
                .uri(uri)
                .header("Authorization", "OAuth " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, response) -> {
                    var error = objectMapper.convertValue(response.getBody(), YandexApiResponseError.class);
                    throw new ResponseStatusException(response.getStatusCode(), error.message());
                })
                .body(YandexApiResponseFileUpload.class);
        return result.href();
    }
}
