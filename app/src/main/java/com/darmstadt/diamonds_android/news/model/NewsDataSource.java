package com.darmstadt.diamonds_android.news.model;

import com.darmstadt.diamonds_android.global.endpoint.DiamondsEndpoint;
import com.darmstadt.diamonds_android.global.util.Urls;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsDataSource;
import com.darmstadt.diamonds_android.news.contract.NewsContract.IOnNewsAvailableCallback;
import com.darmstadt.diamonds_android.news.view.LocalNewsBean;
import com.orhanobut.logger.Logger;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class NewsDataSource implements INewsDataSource {

    private static final String LOG_TAG = NewsDataSource.class.getSimpleName();

    private DiamondsEndpoint mEndpoint;

    public NewsDataSource(final DiamondsEndpoint endpoint) {
        mEndpoint = endpoint;
    }

    @Override
    public void loadNews(final int offset, final IOnNewsAvailableCallback callback) {
        mEndpoint.loadNews("20", String.valueOf(offset)).subscribeOn(Schedulers.io())
                .map(this::getLocalNewsBean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LocalNewsBean>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final List<LocalNewsBean> newsList) {
                        callback.onNewsAvailable(newsList);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Logger.log(Logger.ERROR, LOG_TAG, "Error loading news", e);
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<LocalNewsBean> getLocalNewsBean(final List<RemoteNewsBean> remoteNewsBeanList) {
        final List<LocalNewsBean> localNewsBeanList = new ArrayList<>();
        for (int i = 0; i < remoteNewsBeanList.size(); i++) {
            final RemoteNewsBean bean = remoteNewsBeanList.get(i);
            final LocalNewsBean localNewsBean = new LocalNewsBean();
//            localNewsBean.setCategory(bean.getCategory());
//            localNewsBean.setDate(bean.getDate());
            localNewsBean.setHeadline(bean.getHeadline());
//            localNewsBean.setId(bean.getId());
            localNewsBean.setId(bean.getId());
//            localNewsBean.setLinkExtern(bean.getLinkExtern());
            localNewsBean.setImageHeaderFile(String.format("%s/%s", Urls.DIAMONDS_BASE_URL, Urls.HEADER_IMAGES_PATH.replace("$imageFile", bean.getImageHeaderFile())));
            localNewsBeanList.add(localNewsBean);
        }
        return localNewsBeanList;
    }
}
