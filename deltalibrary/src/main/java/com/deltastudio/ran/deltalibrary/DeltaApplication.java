package com.deltastudio.ran.deltalibrary;

import android.app.Application;

import com.deltastudio.ran.deltalibrary.dagger.components.ApplicationComponent;
import com.deltastudio.ran.deltalibrary.dagger.components.DaggerApplicationComponent;
import com.deltastudio.ran.deltalibrary.dagger.modules.ApplicationModule;

public class DeltaApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}