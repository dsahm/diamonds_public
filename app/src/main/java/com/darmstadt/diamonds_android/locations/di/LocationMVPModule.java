package com.darmstadt.diamonds_android.locations.di;

import com.darmstadt.diamonds_android.global.di.ApiModule;
import com.darmstadt.diamonds_android.global.endpoint.NewDiamondsEndpoint;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.ILocationsDataSource;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.ILocationsPresenter;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.ILocationsView;
import com.darmstadt.diamonds_android.locations.model.LocationDataSource;
import com.darmstadt.diamonds_android.locations.presenter.LocationPresenter;
import dagger.Module;
import dagger.Provides;

@Module(includes = ApiModule.class)
public class LocationMVPModule {

    private ILocationsView locationsView;

    public LocationMVPModule(final ILocationsView locationsView) {
        this.locationsView = locationsView;
    }

    @Provides
    ILocationsPresenter getPresenter(final ILocationsDataSource dataSource) {
        return new LocationPresenter(this.locationsView, dataSource);
    }

    @Provides
    ILocationsDataSource getDataSource(final NewDiamondsEndpoint endpoint) {
        return new LocationDataSource(endpoint);
    }
}

