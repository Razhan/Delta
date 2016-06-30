package com.deltastudio.ran.deltalibrary.presentation;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.deltastudio.ran.deltalibrary.view.MvpView;

import java.lang.ref.WeakReference;

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

    @UiThread
    @Override
    public void attachView(V view) {
        viewRef = new WeakReference(view);
    }

    @UiThread
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @UiThread
    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }

        if (!retainInstance) {
            unsubscribe();
        }
    }

    protected abstract void unsubscribe();
}
