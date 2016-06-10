package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    void onCreate(Bundle saved);

    void onDestroy();

    void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    void onDestroyView();

    void onPause();

    void onResume();

    void onStart();

    void onStop();

    void onActivityCreated(Bundle savedInstanceState);

    void onAttach(Context context);

    void onDetach();

    void onSaveInstanceState(Bundle outState);
}