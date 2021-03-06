package com.deltastudio.ran.deltalibrary.presentation.ice;

import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.TextView;

import com.deltastudio.ran.deltalibrary.R;
import com.deltastudio.ran.deltalibrary.presentation.MvpActivity;
import com.deltastudio.ran.deltalibrary.presentation.MvpPresenter;

public abstract class MvpLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> implements MvpLceView<M> {

    protected View loadingView;
    protected CV contentView;
    protected TextView errorView;

    @CallSuper
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        loadingView = findViewById(R.id.loadingView);
        contentView = (CV) findViewById(R.id.contentView);
        errorView = (TextView) findViewById(R.id.errorView);

        if (loadingView == null) {
            throw new NullPointerException(
                    "Loading view is null! Have you specified a loading view in your layout xml file?"
                            + " You have to give your loading View the id R.id.loadingView");
        }

        if (contentView == null) {
            throw new NullPointerException(
                    "Content view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your content View the id R.id.contentView");
        }

        if (errorView == null) {
            throw new NullPointerException(
                    "Error view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your error View the id R.id.contentView");
        }

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClicked();
            }
        });
    }

    protected void onErrorViewClicked() {
        loadData(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

        if (!pullToRefresh) {
            animateLoadingViewIn();
        }
    }

    protected void animateLoadingViewIn() {
        LceAnimator.showLoading(loadingView, contentView, errorView);
    }

    @Override
    public void showContent() {
        animateContentViewIn();
    }

    protected void animateContentViewIn() {
        LceAnimator.showContent(loadingView, contentView, errorView);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

        String errorMsg = getErrorMessage(e);

        if (pullToRefresh) {
            showMessage(errorMsg);
        } else {
            errorView.setText(errorMsg);
            animateErrorViewIn();
        }
    }

    protected void animateErrorViewIn() {
        LceAnimator.showErrorView(loadingView, contentView, errorView);
    }
}


