package com.darmstadt.diamonds_android.news.contract;

public class NewsDetailContract {

    public interface INewsDetailView {

        void setDetailMessage(final String message);
        void showError();
        boolean uiMemberNullCheck();

    }

    public interface INewsDetailPresenter {

        void loadNewsDetail(final int id);

    }

    public interface INewsDetailDataSource {

        void loadNewsDetail(final int id, final IOnNewsDetailAvailableCallback callback);

    }

    public interface IOnNewsDetailAvailableCallback {

        void onMessageAvailable(final String message);
        void onError();

    }

}
