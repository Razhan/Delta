package com.deltastudio.ran.delta.domain;

import android.util.Log;

import com.deltastudio.ran.deltalibrary.domain.annotation.UseCaseMethod;
import com.deltastudio.ran.deltalibrary.domain.usecase.UseCase;

/**
 * Created by ranzh on 6/30/2016.
 */
public class NewsListUseCase extends UseCase {

    @UseCaseMethod(name = "getNews")
    public void getNews(String arg1, String arg2) {
        Log.d("getNews", "getNews");
        Log.d(arg1, arg2);
    }




}
