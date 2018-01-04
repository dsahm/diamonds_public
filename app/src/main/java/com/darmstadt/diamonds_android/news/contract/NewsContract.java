package com.darmstadt.diamonds_android.news.contract;

import com.darmstadt.diamonds_android.news.view.LocalNewsBean;
import java.util.List;

public class NewsContract {

    public interface INewsView {

        void showError();
        void setNewsData(final List<LocalNewsBean> newsList);
        boolean uiMemberNullCheck();

    }

    public interface INewsPresenter {

        void loadNews(final int offset);

    }

    public interface INewsDataSource {

        void loadNews(final int offset, final IOnNewsAvailableCallback callback);

    }

    public interface IOnNewsAvailableCallback {

        void onNewsAvailable(final List<LocalNewsBean> newsList);
        void onError();

    }
}
