package com.darmstadt.diamonds_android.news.presenter;

import com.darmstadt.diamonds_android.news.contract.NewsContract;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsDataSource;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsView;
import com.darmstadt.diamonds_android.news.contract.NewsContract.IOnNewsAvailableCallback;
import com.darmstadt.diamonds_android.news.view.LocalNewsBean;
import java.util.List;


public class NewsPresenter implements NewsContract.INewsPresenter {

    private INewsView mView;
    private INewsDataSource mDataSource;

    public NewsPresenter(final INewsView view, final INewsDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
    }

    @Override
    public void loadNews(final int offset) {
        mDataSource.loadNews(offset, new IOnNewsAvailableCallback() {
            @Override
            public void onNewsAvailable(final List<LocalNewsBean> newsList) {
                if (mView != null) {
                    mView.setNewsData(newsList);
                }
            }

            @Override
            public void onError() {
                if (mView != null) {
                    mView.showError();
                }
            }
        });
    }
}
