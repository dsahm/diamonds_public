package com.darmstadt.diamonds_android.news.presenter;

import com.darmstadt.diamonds_android.news.contract.NewsDetailContract;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailDataSource;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailView;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.IOnNewsDetailAvailableCallback;


public class NewsDetailPresenter implements NewsDetailContract.INewsDetailPresenter{

    private INewsDetailView mView;
    private INewsDetailDataSource mDataSource;

    public NewsDetailPresenter(final INewsDetailView view, final INewsDetailDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
    }

    @Override
    public void loadNewsDetail(final int id) {
        mDataSource.loadNewsDetail(id, new IOnNewsDetailAvailableCallback() {
            @Override
            public void onMessageAvailable(final String message) {
                if (mView != null) {
                    mView.setDetailMessage(message);
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
