package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.content.Context;
import android.os.Parcelable;

import com.deltastudio.ran.deltalibrarycommon.MvpPresenter;
import com.deltastudio.ran.deltalibrarycommon.MvpView;

public class ViewGroupMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        implements ViewGroupMvpDelegate<V, P> {

    // TODO allow custom save state hook in

    protected ViewGroupDelegateCallback<V, P> delegateCallback;
    protected OrientationChangeManager<V, P> orientationChangeManager =
            new OrientationChangeManager<>();
    protected int viewId = -1;

    public ViewGroupMvpDelegateImpl(ViewGroupDelegateCallback<V, P> delegateCallback) {
        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.delegateCallback = delegateCallback;
    }

    @Override
    public void onAttachedToWindow() {

        // Try to reuse presenter instance from (before screen orientation changes)
        if (delegateCallback.isRetainInstance()) {
            P presenter = orientationChangeManager.getPresenter(viewId, delegateCallback.getContext());
            if (presenter != null) {
                delegateCallback.setPresenter(presenter);
                presenter.attachView(delegateCallback.getMvpView());
                return;
            }
        }

        // TODO clean up that part (double getPresenter check)
        P presenter = delegateCallback.getPresenter();
        if (presenter == null) {
            presenter = delegateCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }

        delegateCallback.setPresenter(presenter);
        if (delegateCallback.isRetainInstance()) {
            viewId = orientationChangeManager.nextViewId(delegateCallback.getContext());
            orientationChangeManager.putPresenter(viewId, presenter, delegateCallback.getContext());
        }

        presenter.attachView(delegateCallback.getMvpView());
    }

    @Override
    public void onDetachedFromWindow() {

        if (delegateCallback.isRetainInstance()) {
            Context context = delegateCallback.getContext();

            boolean destroyedPermanently =
                    orientationChangeManager.willViewBeDestroyedPermanently(context);

            if (destroyedPermanently) {
                // Whole activity will be destroyed
                // Internally Orientation manager already does the clean up
                viewId = 0;
                delegateCallback.getPresenter().detachView(false);
            } else {
                boolean detachedBecauseOrientationChange =
                        orientationChangeManager.willViewBeDetachedBecauseOrientationChange(context);
                if (detachedBecauseOrientationChange) {
                    // Simple orientation change
                    delegateCallback.getPresenter().detachView(true);
                } else {
                    // view detached, i.e. because of back stack / navigation
                    orientationChangeManager.removePresenterAndViewState(viewId, context);
                    viewId = 0;
                    delegateCallback.getPresenter().detachView(false);
                }
            }
        } else {
            // retain instance feature disabled
            delegateCallback.getPresenter().detachView(false);
        }

        // Important cleanup to avoid memory leaks
        orientationChangeManager.cleanUp();
    }

    public Parcelable onSaveInstanceState() {

        Parcelable superState = delegateCallback.superOnSaveInstanceState();

        if (delegateCallback.isRetainInstance()) {
            return createSavedState(superState);
        } else {
            return superState;
        }
    }

    protected MosbySavedState createSavedState(Parcelable superState) {
        MosbySavedState state = new MosbySavedState(superState);
        state.setMosbyViewId(viewId);
        return state;
    }

    protected void restoreSavedState(MosbySavedState state) {
        viewId = state.getMosbyViewId();
    }

    public void onRestoreInstanceState(Parcelable state) {

        if (!(state instanceof MosbySavedState)) {
            delegateCallback.superOnRestoreInstanceState(state);
            return;
        }

        MosbySavedState savedState = (MosbySavedState) state;
        restoreSavedState(savedState);
        delegateCallback.superOnRestoreInstanceState(savedState.getSuperState());
    }
}
