package com.darmstadt.diamonds_android.locations.presenter;

import com.darmstadt.diamonds_android.locations.contract.LocationContract;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.ILocationsDataSource;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.ILocationsView;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.IOnLocationsAvailableCallback;
import com.darmstadt.diamonds_android.locations.view.LocalLocationBean;
import java.util.List;


public class LocationPresenter implements LocationContract.ILocationsPresenter {

    private ILocationsView mView;
    private ILocationsDataSource mDataSource;

    public LocationPresenter(final ILocationsView view, final ILocationsDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
    }

    @Override
    public void loadLocation(final String locationType) {
        mDataSource.loadLocation(locationType, new IOnLocationsAvailableCallback() {
            @Override
            public void onDataAvailable(final List<LocalLocationBean> locations) {
                if (mView != null) {
                    mView.setData(locations);
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
