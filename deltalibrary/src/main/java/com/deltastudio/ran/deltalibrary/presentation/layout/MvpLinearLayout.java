package com.deltastudio.ran.deltalibrary.presentation.layout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.deltastudio.ran.deltalibrary.presentation.delegate.ViewGroupDelegateCallback;
import com.deltastudio.ran.deltalibrary.presentation.delegate.ViewGroupMvpDelegate;
import com.deltastudio.ran.deltalibrary.presentation.delegate.ViewGroupMvpDelegateImpl;
import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public abstract class MvpLinearLayout<V extends MvpView, P extends MvpPresenter<V>>
        extends LinearLayout implements MvpView, ViewGroupDelegateCallback<V, P> {

    protected P presenter;
    protected ViewGroupMvpDelegate<V, P> mvpDelegate;
    private boolean retainInstance = false;

    public MvpLinearLayout(Context context) {
        super(context);
    }

    public MvpLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NonNull
    protected ViewGroupMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ViewGroupMvpDelegateImpl<>(this);
        }

        return mvpDelegate;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getMvpDelegate().onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getMvpDelegate().onDetachedFromWindow();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected Parcelable onSaveInstanceState() {
        return getMvpDelegate().onSaveInstanceState();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        getMvpDelegate().onRestoreInstanceState(state);
    }

    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainInstance() {
        return retainInstance;
    }

    @Override
    public void setRetainInstance(boolean retainingInstance) {
        this.retainInstance = retainingInstance;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return false;
    }

    @Override
    public final Parcelable superOnSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public final void superOnRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
