package com.darmstadt.diamonds_android.dates.di;

import com.darmstadt.diamonds_android.dates.contract.DatesContract.IDatesDataSource;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IDatesPresenter;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IDatesView;
import com.darmstadt.diamonds_android.dates.model.DatesDataSource;
import com.darmstadt.diamonds_android.dates.presenter.DatesPresenter;
import com.darmstadt.diamonds_android.global.di.ApiModule;
import com.darmstadt.diamonds_android.global.endpoint.DampsEndpoint;
import dagger.Module;
import dagger.Provides;

@Module(includes = ApiModule.class)
public class DatesMVPModule {

    private IDatesView datesView;

    public DatesMVPModule(final IDatesView datesView) {
        this.datesView = datesView;
    }

    @Provides
    IDatesPresenter getPresenter(final IDatesDataSource dataSource) {
        return new DatesPresenter(this.datesView, dataSource);
    }

    @Provides
    IDatesDataSource getDataSource(final DampsEndpoint endpoint) {
        return new DatesDataSource(endpoint);
    }
}

