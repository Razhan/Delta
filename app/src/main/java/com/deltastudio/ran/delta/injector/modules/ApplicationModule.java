package com.deltastudio.ran.delta.injector.modules;

import android.app.Application;
import android.content.Context;

import com.deltastudio.ran.delta.ErrorMessageDeterminer;
import com.deltastudio.ran.delta.data.repostory.RestfulService;
import com.deltastudio.ran.deltalibrary.domain.exception.ErrorMessageFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    RestfulService provideRestfulService() {
        return RestfulService.Creator.newService();
    }

    @Provides
    @Singleton
    ErrorMessageFactory provideErrorMessageFactory() {
        return new ErrorMessageDeterminer();
    }

}
