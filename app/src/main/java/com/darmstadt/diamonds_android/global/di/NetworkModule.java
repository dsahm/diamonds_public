package com.darmstadt.diamonds_android.global.di;

import android.content.Context;
import com.darmstadt.diamonds_android.global.util.NetworkUtil;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    private Context context;

    public NetworkModule(final Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    NetworkUtil.HeaderInterceptor getHeaderInterceptor() {
        return new NetworkUtil.HeaderInterceptor();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    OkHttpClient getHttpClient(final Cache cache, final NetworkUtil.HeaderInterceptor headerInterceptor, final HttpLoggingInterceptor httpInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .cache(cache)
                .addInterceptor(httpInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Cache getCache(final File cacheFile, final int cacheSize) {
        return new Cache(cacheFile, cacheSize);
    }

    @Provides
    @Singleton
    int getCacheSize() {
        // 10 MB
        return 10 * 10 * 1024;
    }

    @Provides
    @Singleton
    File getCacheFile() {
        return context.getCacheDir();
    }
}
