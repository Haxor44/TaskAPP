package com.haxor.taskapp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HInterceptor implements Interceptor {
    private String clientId;
    public HInterceptor(String clientId) {
        this.clientId = clientId;
    }
    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Authorization", "Client-ID " + clientId)
                .build();
        return chain.proceed(request);
    }
}
