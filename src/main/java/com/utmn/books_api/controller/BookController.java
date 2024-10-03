package com.utmn.books_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;


@RestController
@RequiredArgsConstructor
public class BookController {

    @Value("${yandex-disk.token}")
    private String token;

    @Value("${yandex-disk.url}")
    private String url;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object upload(@RequestBody MultipartFile file) throws URISyntaxException, IOException, InterruptedException {
        var restClient = RestClient.create();
        URI urls = new URI(url + "?path=/mks/mks4");
        var result = restClient.get()
                .uri(urls)
                .header("Authorization", "OAuth " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ResponseFileUpload.class);

        var reloadUrl = result.getHref();


        uploadFile(reloadUrl, file);


        return result;

    }

    private void uploadFile(String reloadUrl, MultipartFile file) throws IOException, InterruptedException {

        Path filePath = Path.of("C:/Users/Mikhail/Downloads/mks.jpeg");

        // Создание HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Создание HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(reloadUrl))
                .header("Authorization", "")
                .PUT(HttpRequest.BodyPublishers.ofFile(filePath))  // Отправляем файл с методом PUT
                .build();

        // Выполнение запроса
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Вывод ответа
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}
