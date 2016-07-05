package com.deltastudio.ran.delta;

import com.deltastudio.ran.deltalibrary.domain.exception.ErrorMessageFactory;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by ranzh on 7/1/2016.
 */
public class ErrorMessageDeterminer implements ErrorMessageFactory {

    @Override
    public String getErrorMessage(Throwable exception) {
        if (exception instanceof UnknownHostException) {
            return "Network Error: Are you connected to the internet?";
        }

        return "An unknown error has occurred";
    }
}

