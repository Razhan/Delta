package com.deltastudio.ran.deltalibrary.presentation.delegate;

import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public interface ActivityMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpDelegateCallback<V, P> {

    Object saveExtraInstance();

    Object getLastCustomNonConfigurationInstance();

    Object getExtraInstance();
}
