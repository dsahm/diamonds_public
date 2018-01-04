package com.darmstadt.diamonds_android.news.di;

import com.darmstadt.diamonds_android.global.di.ApiModule;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsDataSource;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsPresenter;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsView;
import com.darmstadt.diamonds_android.global.endpoint.DiamondsEndpoint;
import com.darmstadt.diamonds_android.news.model.NewsDataSource;
import com.darmstadt.diamonds_android.news.presenter.NewsPresenter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = ApiModule.class)
public class NewsMVPModule {

    private INewsView view;

    public NewsMVPModule(final INewsView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    INewsDataSource getDataSource(final DiamondsEndpoint endpoint) {
        return new NewsDataSource(endpoint);
    }

    @Provides
    @Singleton
    INewsPresenter getPresenter(final INewsDataSource dataSource) {
        return new NewsPresenter(view, dataSource);
    }



}
