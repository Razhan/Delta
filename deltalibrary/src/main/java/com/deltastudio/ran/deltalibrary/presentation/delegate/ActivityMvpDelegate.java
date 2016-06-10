package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.os.Bundle;

import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    void onCreate(Bundle bundle);

    void onDestroy();

    void onPause();

    void onResume();

    void onStart();

    void onStop();

    void onRestart();

    void onContentChanged();

    void onSaveInstanceState(Bundle outState);

    void onPostCreate(Bundle savedInstanceState);

    Object onRetainCustomNonConfigurationInstance();

    Object getConfigurationInstance();
}
