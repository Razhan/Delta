package com.deltastudio.ran.delta.injector.components;

import android.content.Context;

import com.deltastudio.ran.delta.data.repostory.RestfulService;
import com.deltastudio.ran.delta.injector.modules.ApplicationModule;
import com.deltastudio.ran.deltalibrary.domain.exception.ErrorMessageFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();

    RestfulService getRestfulService();

    ErrorMessageFactory getErrorMessageFactory();
}
