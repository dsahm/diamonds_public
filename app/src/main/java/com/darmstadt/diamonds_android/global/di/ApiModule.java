package com.darmstadt.diamonds_android.global.di;

import com.darmstadt.diamonds_android.global.endpoint.DampsEndpoint;
import com.darmstadt.diamonds_android.global.endpoint.NewDiamondsEndpoint;
import com.darmstadt.diamonds_android.global.util.Urls;
import com.darmstadt.diamonds_android.global.endpoint.DiamondsEndpoint;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiModule {

    public static final String DIAMONDS_BASE_URL = "base_url";
    public static final String DAMPS_BASE_URL = "damps_base_url";
    public static final String NEW_DIAMONDS_BASE_URL = "new_diamonds_base_rul";

    @Provides
    @Named(DIAMONDS_BASE_URL)
    String getDiamondsBaseUrl() {
        return Urls.DIAMONDS_BASE_URL;
    }

    @Provides
    @Named(DAMPS_BASE_URL)
    String getDampsBaseUrl() {
        return Urls.SERVICE_URL;
    }

    @Provides
    @Named(NEW_DIAMONDS_BASE_URL)
    String getNewDiamondsBaseUrl() {
        return Urls.NEW_DIAMONDS_BASE_URL;
    }

    @Provides
    @Singleton
    @Named(DIAMONDS_BASE_URL)
    Retrofit getDiamondsRetrofit(final OkHttpClient okHttpClient, @Named(DIAMONDS_BASE_URL) final String baseUrl, final Converter.Factory converterFactory, final CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named(DAMPS_BASE_URL)
    Retrofit getDampsRetrofit(final OkHttpClient okHttpClient, @Named(DAMPS_BASE_URL) final String baseUrl, final Converter.Factory converterFactory, final CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named(NEW_DIAMONDS_BASE_URL)
    Retrofit getNewDiamondRetrofit(final OkHttpClient okHttpClient, @Named(NEW_DIAMONDS_BASE_URL) final String baseUrl, final Converter.Factory converterFactory, final CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory getRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    DiamondsEndpoint getDiamondsEndpoint(@Named(DIAMONDS_BASE_URL) final Retrofit retrofit) {
        return retrofit.create(DiamondsEndpoint.class);
    }

    @Provides
    @Singleton
    DampsEndpoint getDampsEndoint(@Named(DAMPS_BASE_URL) final Retrofit retrofit) {
        return retrofit.create(DampsEndpoint.class);
    }

    @Provides
    @Singleton
    NewDiamondsEndpoint getNewDiamondsEndpoint(@Named(NEW_DIAMONDS_BASE_URL) final Retrofit retrofit) {
        return retrofit.create(NewDiamondsEndpoint.class);
    }

}
