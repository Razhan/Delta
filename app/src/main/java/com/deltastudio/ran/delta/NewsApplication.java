package com.deltastudio.ran.delta;

import android.app.Application;

import com.deltastudio.ran.delta.injector.components.ApplicationComponent;
import com.deltastudio.ran.delta.injector.components.DaggerApplicationComponent;
import com.deltastudio.ran.delta.injector.modules.ApplicationModule;


/**
 * Created by ranzh on 6/30/2016.
 */
public class NewsApplication extends Application {

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