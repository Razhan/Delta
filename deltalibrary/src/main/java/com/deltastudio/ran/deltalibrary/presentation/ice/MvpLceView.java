package com.deltastudio.ran.deltalibrary.presentation.ice;

import android.support.annotation.UiThread;

import com.deltastudio.ran.deltalibrary.view.MvpView;

public interface MvpLceView<M> extends MvpView {

    @UiThread
    void showLoading(boolean pullToRefresh);


    @UiThread
    void showContent();

    @UiThread
    void showError(Throwable e, boolean pullToRefresh);

    @UiThread
    void setData(M data);

    @UiThread
    void loadData(boolean pullToRefresh);

}
