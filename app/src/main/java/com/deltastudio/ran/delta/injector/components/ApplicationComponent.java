package com.deltastudio.ran.delta.injector.components;

import android.content.Context;

import com.deltastudio.ran.delta.injector.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();
}
