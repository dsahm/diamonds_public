package com.darmstadt.diamonds_android.news.model;

import com.darmstadt.diamonds_android.global.endpoint.DiamondsEndpoint;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailDataSource;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.IOnNewsDetailAvailableCallback;
import com.orhanobut.logger.Logger;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailDataSource implements INewsDetailDataSource {

    private static final String LOG_TAG = NewsDetailDataSource.class.getSimpleName();

    private DiamondsEndpoint mEndpoint;

    public NewsDetailDataSource(final DiamondsEndpoint endpoint) {
        mEndpoint = endpoint;
    }

    @Override
    public void loadNewsDetail(final int newsId, final IOnNewsDetailAvailableCallback callback) {
        mEndpoint.loadNewsDetail(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetailBean>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final NewsDetailBean newsDetailBean) {
                        try {
                            if (newsDetailBean.getLinkStory() != null) {
                                callback.onMessageAvailable(newsDetailBean.getLinkStory().getText());
                            } else {
                                callback.onMessageAvailable(newsDetailBean.getText());
                            }
                        } catch (NullPointerException e) {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Logger.log(Logger.ERROR, LOG_TAG,"Error loading news detail", e);
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
