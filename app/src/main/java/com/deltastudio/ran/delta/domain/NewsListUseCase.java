package com.deltastudio.ran.delta.domain;

import android.util.Log;

import com.deltastudio.ran.delta.data.model.News;
import com.deltastudio.ran.delta.data.repostory.NewsRepository;
import com.deltastudio.ran.deltalibrary.domain.annotation.UseCaseMethod;
import com.deltastudio.ran.deltalibrary.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ranzh on 6/30/2016.
 */
public class NewsListUseCase extends UseCase {

    private NewsRepository newsRepository;

    @Inject
    public NewsListUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @UseCaseMethod(name = "getNews")
    public Observable<News> getNews() {
        return newsRepository.getRestfulService().getNews();
    }

}
