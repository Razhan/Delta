package com.deltastudio.ran.delta.presenter;

import android.content.Context;

import com.deltastudio.ran.delta.data.model.News;
import com.deltastudio.ran.delta.view.MainView;
import com.deltastudio.ran.deltalibrary.domain.usecase.UseCase;
import com.deltastudio.ran.deltalibrary.presentation.MvpBasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ranzh on 6/30/2016.
 */
public class MainPresenter extends MvpBasePresenter<MainView> {

    private final UseCase<News> newsUseCase;

    @Inject
    public MainPresenter(@Named("NewsList") UseCase<News> newsUseCase, Context context) {
        this.newsUseCase = newsUseCase;
        this.mContext = context;
    }

    public void getNews(boolean pullToRefresh) {
//        getView().showLoading(pullToRefresh);

        newsUseCase.builder()
                    .useCaseFunction("getNews")
                    .onSuccess(news -> {
//                        if (isViewAttached()) {
//                            getView().setData(news);
//                            getView().showContent();
//                        }
                        news.getCount();
                    })
                    .onError(e -> {
                        e.printStackTrace();
                    })
                    .build();
    }

    @Override
    protected void unsubscribe() {
        newsUseCase.unsubscribe();
    }
}
