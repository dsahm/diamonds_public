package com.darmstadt.diamonds_android.locations.model;

import com.darmstadt.diamonds_android.global.endpoint.NewDiamondsEndpoint;
import com.darmstadt.diamonds_android.global.util.TextUtil;
import com.darmstadt.diamonds_android.locations.contract.LocationContract;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.IOnLocationsAvailableCallback;
import com.darmstadt.diamonds_android.locations.view.LocalLocationBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;


public class LocationDataSource implements LocationContract.ILocationsDataSource {

    private static final String LOG_TAG = LocationDataSource.class.getSimpleName();

    private NewDiamondsEndpoint mEndpoint;

    public LocationDataSource(final NewDiamondsEndpoint endpoint) {
        mEndpoint = endpoint;
    }

    @Override
    public void loadLocation(final String locationType, final IOnLocationsAvailableCallback callback) {
        mEndpoint.loadLocations("location", locationType)
                .subscribeOn(Schedulers.io())
                .map(this::getLocalLocationBean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LocalLocationBean>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final List<LocalLocationBean> locationList) {
                        callback.onDataAvailable(locationList);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<LocalLocationBean> getLocalLocationBean(final LocationResponseBean response) {
        final List<LocalLocationBean> localLocationBeans = new ArrayList<>();
        final List<LocationResponseBean.Data> data = response.getDataList();
        for (int i = 0; i < data.size(); i++) {
            // TODO: Optimize all of this with dagger, check if header is necessary
            // Header level
            final LocationResponseBean.Data dataBean = data.get(i);
            final LocalLocationBean localLocationBean = new LocalLocationBean();
            try {
                localLocationBean.setLatitude(Double.parseDouble(dataBean.getLatitude()));
                localLocationBean.setLongitude(Double.parseDouble(dataBean.getLongitude()));
            } catch (Exception e) {
                localLocationBean.setLongitude(0);
                localLocationBean.setLatitude(0);
            }
            final String street = TextUtil.fromHtml(dataBean.getStreet()).toString();
            final String streetNumber = TextUtil.fromHtml(dataBean.getStreetNumber()).toString();
            final String zip = TextUtil.fromHtml(dataBean.getZip()).toString();
            final String city = TextUtil.fromHtml(dataBean.getCity()).toString();

            localLocationBean.setLocation(String.format("%s %s\n%s %s", street, streetNumber, zip, city));
            localLocationBean.setTitle(TextUtil.fromHtml(dataBean.getName()).toString());
            localLocationBean.setSubTitle(TextUtil.fromHtml(dataBean.getTypeName()).toString());
            localLocationBeans.add(localLocationBean);
        }
        return localLocationBeans;
    }
}
