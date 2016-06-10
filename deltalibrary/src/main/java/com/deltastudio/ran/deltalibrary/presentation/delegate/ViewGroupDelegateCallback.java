package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.content.Context;
import android.os.Parcelable;

import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public interface ViewGroupDelegateCallback<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpDelegateCallback<V, P> {

    Parcelable superOnSaveInstanceState();

    void superOnRestoreInstanceState(Parcelable state);

    Context getContext();
}
