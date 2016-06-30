package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.support.annotation.NonNull;

import com.deltastudio.ran.deltalibrary.presentation.MvpPresenter;
import com.deltastudio.ran.deltalibrary.view.MvpView;

public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    @NonNull
    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();

    boolean isRetainInstance();

    void setRetainInstance(boolean retainingInstance);

    boolean shouldInstanceBeRetained();
}

