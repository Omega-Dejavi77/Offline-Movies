package com.example.offlinemovies.data;

import com.example.offlinemovies.data.remote.ApiConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("api_key", ApiConstants.API_KEY)
                .build();

        Request request = chain.request().newBuilder()
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
