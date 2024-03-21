package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;

@Slf4j
@Component
public class OkhttpUtils {


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static final MediaType XML = MediaType.get("text/xml");

    @Resource
    private OkHttpClient okHttpClient;

    public String post(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        } catch (SocketTimeoutException e) {
            log.error("[OkHttpClientUtils#post] 请求超时。url={}, e={}", url, e);
            throw new RuntimeException("Okhttp timeout.");
        } catch (Exception e) {
            log.error("[OkHttpClientUtils#post] 请求异常。url={}, e={}", url, e);
            return null;
        }
    }


    public String postXml(String url, String requestBody) {
        RequestBody body = RequestBody.create(XML, requestBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "text/xml; charset=utf-8")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.info("[OkHttpClientUtils#postXml] 返回失败。url={}, response={}", url, response);
                return null;
            }
            return response.body().string();
        } catch (SocketTimeoutException e) {
            log.error("[OkHttpClientUtils#post] 请求超时。url={}, e={}", url, e);
            throw new RuntimeException("Okhttp timeout.");
        } catch (Exception e) {
            log.error("[OkHttpClientUtils#postXml] 请求异常。url={}, e={}", url, e);
            return null;
        }
    }

}
