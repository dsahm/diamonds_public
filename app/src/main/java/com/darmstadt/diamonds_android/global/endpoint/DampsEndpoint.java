package com.darmstadt.diamonds_android.global.endpoint;

import com.darmstadt.diamonds_android.dates.model.PracticeResponseBean;
import com.darmstadt.diamonds_android.dates.model.ScheduleResponseBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DampsEndpoint {

    @GET("XXX")
    Observable<ScheduleResponseBean> loadSchedule(@Query("unit_id") final int unitId);

    @GET("XXX")
    Observable<PracticeResponseBean> loadPractice();

}
