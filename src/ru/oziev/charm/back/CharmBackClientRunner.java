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

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.yandex.ru"))
                .setHeader("My-Token", "jdkjfafjdfjdf")
                .POST(HttpRequest.BodyPublishers.ofString("dsa"))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, List<String>> map = httpResponse.headers().map();

        System.out.println(httpResponse.statusCode());
        System.out.println();
        System.out.println(map);
        System.out.println(httpResponse.body());
    }
}
