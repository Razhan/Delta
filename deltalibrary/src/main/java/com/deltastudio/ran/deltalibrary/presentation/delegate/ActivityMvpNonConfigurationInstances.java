package com.deltastudio.ran.deltalibrary.presentation.delegate;


import com.deltastudio.ran.deltalibrary.presentation.MvpPresenter;
import com.deltastudio.ran.deltalibrary.view.MvpView;

class ActivityMvpNonConfigurationInstances<V extends MvpView, P extends MvpPresenter<V>> {

    P presenter;

    Object configurationInstance;

    ActivityMvpNonConfigurationInstances(P presenter, Object configurationInstance) {
        this.presenter = presenter;
        this.configurationInstance = configurationInstance;
    }
}
