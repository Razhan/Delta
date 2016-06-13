package com.deltastudio.ran.deltalibrary.dagger.components;

import android.content.Context;

import com.deltastudio.ran.deltalibrary.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();

}
