package com.darmstadt.diamonds_android.locations.contract;


import android.support.annotation.StringDef;
import com.darmstadt.diamonds_android.locations.view.LocalLocationBean;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class LocationContract {

    public interface ILocationsView {

        void setData(final List<LocalLocationBean> schedules);
        void showError();
        boolean uiMemberNullCheck();

    }

    public interface ILocationsPresenter {

        void loadLocation(final String locationType);

    }

    public interface ILocationsDataSource {

        void loadLocation(final String locationType, final IOnLocationsAvailableCallback callback);

    }

    public interface IOnLocationsAvailableCallback {

        void onDataAvailable(final List<LocalLocationBean> locations);
        void onError();
    }

    public interface IAdapterClickListener {
        void onItemExpanded(final int position);
    }

    @StringDef({LocationType.HOME, LocationType.AWAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LocationType {
        String HOME = "1|2|3";
        String AWAY = "4";
    }

}
