package com.example.flightticket.API;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .header("X-RapidApi-Host", API.HOST_HEADER)
                .header("X-RapidApi-Key", API.KEY_HEADER)
                .build());
    }
}
