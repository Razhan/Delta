package com.deltastudio.ran.deltalibrary.presentation.delegate;

import com.deltastudio.ran.deltalibrary.presentation.MvpPresenter;
import com.deltastudio.ran.deltalibrary.view.MvpView;

public interface ActivityMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpDelegateCallback<V, P> {

    Object setExtraInstance();

    Object getExtraInstance();

    Object getAllInstance();

    Object setAllInstance();

}
