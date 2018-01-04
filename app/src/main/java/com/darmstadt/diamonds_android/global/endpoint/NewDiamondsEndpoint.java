package com.darmstadt.diamonds_android.global.endpoint;

import com.darmstadt.diamonds_android.locations.model.LocationResponseBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewDiamondsEndpoint {

    @GET("XXX")
    Observable<LocationResponseBean> loadLocations(@Query("action") final String action, @Query("typeid") final String typeId);

}
