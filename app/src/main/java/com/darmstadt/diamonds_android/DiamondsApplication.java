package com.darmstadt.diamonds_android;

import android.app.Application;
import android.support.annotation.UiThread;
import com.darmstadt.diamonds_android.global.di.ApplicationComponent;
import com.darmstadt.diamonds_android.global.di.DaggerApplicationComponent;
import com.darmstadt.diamonds_android.global.di.NetworkModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import javax.inject.Inject;

public class DiamondsApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    AndroidLogAdapter mLogAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        getApplicationComponent().inject(this);
        Logger.addLogAdapter(mLogAdapter);
    }

    @UiThread
    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .networkModule(new NetworkModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
