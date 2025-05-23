package org.example;

import com.google.gson.Gson;

import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DashTest {

    @Test
    public void checkSuccessful() throws Exception {
        Gson gson = new Gson();
        SimpleEntity simpleEntity;
        String token;
        String lifeTime;
        String result;
        //
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody;
        Request request;
        Response response;
        String resultJson;
        //
        // Get TokenLifetime
        request = new Request
                .Builder()
                .url("http://localhost:8080/tokenlifetime")
                .get()
                .build();
        response = client.newCall(request).execute();
        ResponseBody body = response.body();
        Assertions.assertNotNull(body);
        resultJson = body.string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        lifeTime = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals("300000", lifeTime);
        Assertions.assertEquals(200, response.code());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Login
        formBody = new FormBody.Builder()
                .add("name", "admin")
                .add("password", "qwerty")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/login")
                .post(formBody)
                .build();
        response = client.newCall(request).execute();
        Assertions.assertNotNull(body);
        resultJson = body.string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        token = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Update TokenLifetime
        formBody = new FormBody.Builder()
                .add("token", token)
                .add("time", "901000")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/tokenlifetime")
                .put(formBody)
                .build();
        response = client.newCall(request).execute();
        Assertions.assertNotNull(body);
        resultJson = body.string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        lifeTime = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals("true", lifeTime);
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Get NEW TokenLifetime
        request = new Request.Builder()
                .url("http://localhost:8080/tokenlifetime")
                .get()
                .build();
        response = client.newCall(request).execute();
        Assertions.assertNotNull(body);
        resultJson = body.string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        lifeTime = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals("901000", lifeTime);
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Logout
        formBody = new FormBody.Builder()
                .add("name", "admin")
                .add("token", token)
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/logout")
                .post(formBody)
                .build();
        response = client.newCall(request).execute();
        Assertions.assertNotNull(body);
        resultJson = body.string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Reset Server
        request = new Request.Builder()
                .url("http://localhost:8080/reset")
                .get()
                .build();
        response = client.newCall(request).execute();
        Assertions.assertNotNull(body);
        resultJson = body.string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals("true", result);
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
    }

    @Test
    public void checkUser() throws Exception {
        Gson gson = new Gson();
        SimpleEntity simpleEntity;
        String token;
        String lifeTime;
        String result;
        //
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody;
        Request request;
        Response response;
        String resultJson;
        //
        // Login
        formBody = new FormBody.Builder()
                .add("name", "admin")
                .add("password", "qwerty")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/login")
                .post(formBody)
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        token = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Get all Users
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8080/users").newBuilder();
        urlBuilder.addQueryParameter("token", token);
        String url = urlBuilder.build().toString();
        System.out.println("\turl = " + url);
        //
        request = new Request
                .Builder()
                //.url("http://localhost:8080/users?token=" + token)
                .url(url)
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        System.out.println("result: " + result);
        //
        // Delete users
        formBody = new FormBody.Builder()
                .add("token", token)
                .add("name", "kilinatc")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/user?token=" + token + "&name=kilinatc")
                //.url("http://localhost:8080/user")
                //.delete(formBody)
                //.addHeader("Content-Length", "52")
                .delete()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        System.out.println("result: " + result);
    }

    @Test
    public void checkSimple() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request;
        Response response;
        String resultJson;
        //
        // Get
        request = new Request
                .Builder()
                .url("http://localhost:8080/")
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(200, response.code());
        System.out.println("resultJson: " + resultJson);
        //
    }
}