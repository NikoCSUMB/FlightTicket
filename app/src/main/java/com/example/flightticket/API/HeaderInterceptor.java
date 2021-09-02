package com.example.flightticket.API;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .header("X-RapidApi-Host", API.HOST_HEADER)
                .header("X-RapidApi-Key", API.KEY)
//                .header("Content-Type", API.CONTENT_TYPE)
                .build();
    }
}
