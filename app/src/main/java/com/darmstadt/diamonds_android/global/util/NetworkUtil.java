package com.darmstadt.diamonds_android.global.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtil {
    public static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-type", "application/json")
                    .build();
            return chain.proceed(request);
        }
    }
}
