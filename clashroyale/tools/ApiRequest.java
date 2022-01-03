package pl.clashroyale.tools;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {

    public static HttpRequest buildRequest(String url, String token){
        String auth = "Bearer " + token;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
                .GET()
                .build();

        return request;
    }

    public static String getSingleResponse(String url, String token){
        HttpRequest request = buildRequest(url, token);
        HttpClient client = HttpClient.newHttpClient();
        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return response;
    }
}
