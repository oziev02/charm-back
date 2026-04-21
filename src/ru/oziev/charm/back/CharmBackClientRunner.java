package ru.oziev.charm.back;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

// Сокет у нас для клиента (а сервер-сокет для TCP-сервера. Он реализован в другом файле)
public class CharmBackClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://localhost:8080"))
                .GET()
                .build();

        HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
        HttpClient.Version version = response.version();
        int statusCode = response.statusCode();
        String headers = response.headers().toString();
        String body = new String(response.body());
        System.out.println(version + " " + statusCode + "\n" + headers + "\n\n" + body);
    }
}
