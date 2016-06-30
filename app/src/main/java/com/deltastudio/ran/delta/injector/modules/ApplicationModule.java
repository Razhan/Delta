package com.deltastudio.ran.delta.injector.modules;

import android.app.Application;
import android.content.Context;

import com.deltastudio.ran.delta.data.repostory.RestfulService;

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

}
