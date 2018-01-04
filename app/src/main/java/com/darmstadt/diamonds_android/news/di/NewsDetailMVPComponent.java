package com.darmstadt.diamonds_android.news.di;

import com.darmstadt.diamonds_android.news.view.NewsDetailActivity;
import dagger.Subcomponent;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = {NewsDetailMVPModule.class})
public interface NewsDetailMVPComponent {

    void inject(final NewsDetailActivity target);

}
