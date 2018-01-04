package com.darmstadt.diamonds_android.global.endpoint;

import com.darmstadt.diamonds_android.news.model.NewsDetailBean;
import com.darmstadt.diamonds_android.news.model.RemoteNewsBean;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiamondsEndpoint {

    @GET("XXX")
    Observable<List<RemoteNewsBean>> loadNews(@Query("max") final String max, @Query("offset") final String offset);

    @GET("XXX")
    Observable<NewsDetailBean> loadNewsDetail(@Query("id") final int id);

}
