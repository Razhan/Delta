package com.deltastudio.ran.delta.data.repostory;

import com.deltastudio.ran.delta.data.model.News;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface RestfulService {

    String ENDPOINT = "http://api.guancha.cn/";

    @GET("Appdata/NewsList/?newstype=1&isline=0&size=20&page=1&type=1&id=homepage")
    Observable<News> getNews();

    class Creator {

        public static RestfulService newService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestfulService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(RestfulService.class);
        }
    }
}
