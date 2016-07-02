package com.deltastudio.ran.delta.injector.modules;

import com.deltastudio.ran.delta.ErrorMessageDeterminer;
import com.deltastudio.ran.delta.data.model.News;
import com.deltastudio.ran.delta.domain.NewsListUseCase;
import com.deltastudio.ran.delta.injector.PerActivity;
import com.deltastudio.ran.deltalibrary.domain.usecase.UseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ranzh on 6/30/2016.
 */
@Module
public class NewsModule {

    @Provides
    @PerActivity
    @Named("NewsList")
    UseCase<News> provideNewsListUseCase(NewsListUseCase newsListUseCase) {
        return newsListUseCase;
    }

    @Provides @PerActivity
    public ErrorMessageDeterminer providesErrorMessageDeterminer(){
        return new ErrorMessageDeterminer();
    }

}