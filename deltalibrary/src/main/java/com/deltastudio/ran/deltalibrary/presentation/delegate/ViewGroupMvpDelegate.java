package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.os.Parcelable;

import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public interface ViewGroupMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    void onAttachedToWindow();

    void onDetachedFromWindow();

    void onRestoreInstanceState(Parcelable state);

    Parcelable onSaveInstanceState();
}
