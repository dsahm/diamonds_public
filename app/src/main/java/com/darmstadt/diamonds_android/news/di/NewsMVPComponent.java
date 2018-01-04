package com.darmstadt.diamonds_android.news.di;

import com.darmstadt.diamonds_android.news.view.NewsFragment;
import dagger.Subcomponent;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = {NewsMVPModule.class})
public interface NewsMVPComponent {

    void inject(final NewsFragment target);

}
