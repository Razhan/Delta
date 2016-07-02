package com.deltastudio.ran.delta;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by ranzh on 7/1/2016.
 */
public class ErrorMessageDeterminer {

    public String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e instanceof HttpException) {
            return "Network Error: Are you connected to the internet?";
        }

        return "An unknown error has occurred";
    }
}

