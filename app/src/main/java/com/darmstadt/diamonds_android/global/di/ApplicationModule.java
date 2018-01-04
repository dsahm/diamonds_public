package com.darmstadt.diamonds_android.global.di;

import com.darmstadt.diamonds_android.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    AndroidLogAdapter getLogAdapter() {
        return new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        };
    }
}
