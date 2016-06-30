package com.deltastudio.ran.delta.presenter;

import android.content.Context;

import com.deltastudio.ran.delta.data.model.News;
import com.deltastudio.ran.delta.view.MainView;
import com.deltastudio.ran.deltalibrary.domain.usecase.UseCase;
import com.deltastudio.ran.deltalibrary.presentation.MvpBasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.functions.Action1;

/**
 * Created by ranzh on 6/30/2016.
 */
public class MainPresenter extends MvpBasePresenter<MainView> {

    private final UseCase getNews;

    @Inject
    public MainPresenter(@Named("NewsList") UseCase getUserAccountUseCase, Context context) {
        this.getNews = getUserAccountUseCase;
        this.mContext = context;
    }

    public void getNews() {
        getNews.useCaseName("getNews")
                .onSuccess(news -> {
                    ((News)news).getCount();})
                .onError(e -> {
                    ((Throwable) e).printStackTrace();})
                .execute();
    }

    @Override
    protected void unsubscribe() {
        getNews.unsubscribe();
    }
}
