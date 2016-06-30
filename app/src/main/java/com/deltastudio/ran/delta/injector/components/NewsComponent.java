package com.deltastudio.ran.delta.injector.components;

import com.deltastudio.ran.delta.MainActivity;
import com.deltastudio.ran.delta.injector.PerActivity;
import com.deltastudio.ran.delta.injector.modules.NewsModule;

import dagger.Component;

/**
 * Created by ranzh on 6/30/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = NewsModule.class)
public interface NewsComponent {

    void inject(MainActivity activity);
}