package com.darmstadt.diamonds_android.global.di;

import com.darmstadt.diamonds_android.DiamondsApplication;
import com.darmstadt.diamonds_android.dates.di.DatesMVPComponent;
import com.darmstadt.diamonds_android.dates.di.DatesMVPModule;
import com.darmstadt.diamonds_android.locations.di.LocationMVPComponent;
import com.darmstadt.diamonds_android.locations.di.LocationMVPModule;
import com.darmstadt.diamonds_android.news.di.NewsDetailMVPComponent;
import com.darmstadt.diamonds_android.news.di.NewsDetailMVPModule;
import com.darmstadt.diamonds_android.news.di.NewsMVPComponent;
import com.darmstadt.diamonds_android.news.di.NewsMVPModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(final DiamondsApplication target);
    NewsMVPComponent getNewsMVPComponent(final NewsMVPModule newsMVPModule);
    NewsDetailMVPComponent getNewsDetailMVPComponent(final NewsDetailMVPModule newsDetailMVPModule);
    DatesMVPComponent getDatesMVPComponent(final DatesMVPModule datesMVPModule);
    LocationMVPComponent getLocationMVPComponent(final LocationMVPModule locationMVPModule);

}
