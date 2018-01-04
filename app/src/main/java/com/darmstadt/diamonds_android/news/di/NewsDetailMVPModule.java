package com.darmstadt.diamonds_android.news.di;

import com.darmstadt.diamonds_android.global.di.ApiModule;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailDataSource;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailPresenter;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailView;
import com.darmstadt.diamonds_android.global.endpoint.DiamondsEndpoint;
import com.darmstadt.diamonds_android.news.model.NewsDetailDataSource;
import com.darmstadt.diamonds_android.news.presenter.NewsDetailPresenter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = ApiModule.class)
public class NewsDetailMVPModule {

    private INewsDetailView view;

    public NewsDetailMVPModule(final INewsDetailView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    INewsDetailDataSource getDataSource(final DiamondsEndpoint endpoint) {
        return new NewsDetailDataSource(endpoint);
    }

    @Provides
    @Singleton
    INewsDetailPresenter getPresenter(final INewsDetailDataSource dataSource) {
        return new NewsDetailPresenter(view, dataSource);
    }



}
