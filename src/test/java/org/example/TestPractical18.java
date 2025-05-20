package org.example;

import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

public class TestPractical18 {

    private final OkHttpClient client = new OkHttpClient();


     //Відправляє GET-запит для отримання активних коментарів

    private Response sendGetCommentsRequest(int eventId, int page, int size) throws IOException {
        String BASE_URL = "https://greencity.greencity.cx.ua";
        HttpUrl url = HttpUrl.parse(BASE_URL + "/events/" + eventId + "/comments")
                .newBuilder()
                .addQueryParameter("page", String.valueOf(page))
                .addQueryParameter("size", String.valueOf(size))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();
        System.out.println("Request URL: " + url);
        return response;
    }

    @Test
    public void checkSuccessful() throws IOException {
        Response response = sendGetCommentsRequest(2021, 0, 5);
        ResponseBody body = response.body();

        Assertions.assertNotNull(body);
        String resultJson = body.string();

        Assertions.assertEquals(200, response.code());
        System.out.println("Response JSON: " + resultJson);
    }

    @ParameterizedTest
    @DisplayName("Different values of page and size")
    @CsvSource({
            "2021, 0, 5",
            "2021, 1, 2",
            "2021, 2, 3"
    })
    void parameterizedTest(int eventId, int page, int size) throws IOException {
        Response response = sendGetCommentsRequest(eventId, page, size);
        ResponseBody body = response.body();

        Assertions.assertNotNull(body);
        String resultJson = body.string();

        Assertions.assertEquals(200, response.code());
        System.out.println("Response JSON: " + resultJson);
    }
    @ParameterizedTest
    @DisplayName("Negative test cases for invalid parameters")
    @CsvSource({
            "999999, 0, 5, 404",     // Неіснуючий eventId
            "2021, -1, 5, 400",      // Негативна сторінка
            "2021, 0, -10, 400"    // Негативний size
    })
    void negativeTest(int eventId, int page, int size, int expectedStatus) throws IOException {
        Response response = sendGetCommentsRequest(eventId, page, size);
        int actualResult = response.code();
        System.out.printf("Tested URL with eventId=%d, page=%d, size=%d → expected: %d, actual: %d%n",
                eventId, page, size, expectedStatus, actualResult);
        Assertions.assertEquals(expectedStatus, actualResult,  "Очікуваний код відповіді не збігається з фактичним.");
    }
}


