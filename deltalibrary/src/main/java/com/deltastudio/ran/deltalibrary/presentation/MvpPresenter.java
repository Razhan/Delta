package com.deltastudio.ran.deltalibrary.presentation;

import android.support.annotation.UiThread;

import com.deltastudio.ran.deltalibrary.view.MvpView;

public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void attachView(V view);

    @UiThread
    void detachView(boolean retainInstance);
}
