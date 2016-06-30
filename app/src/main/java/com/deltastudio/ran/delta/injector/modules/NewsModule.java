package com.deltastudio.ran.delta.injector.modules;

import com.deltastudio.ran.delta.domain.NewsListUseCase;
import com.deltastudio.ran.delta.injector.PerActivity;
import com.deltastudio.ran.deltalibrary.domain.usecase.UseCase;

import javax.inject.Named;

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
    UseCase provideNewsListUseCase(NewsListUseCase newsListUseCase) {
        return newsListUseCase;
    }

}