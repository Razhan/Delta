package com.deltastudio.ran.delta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.deltastudio.ran.delta.injector.components.DaggerNewsComponent;
import com.deltastudio.ran.delta.presenter.MainPresenter;
import com.deltastudio.ran.deltalibrary.presentation.ice.MvpLceActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDependencyInjector();

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.getNews();
            }
        });
    }

    protected void initDependencyInjector() {
        DaggerNewsComponent.builder()
                .applicationComponent(((NewsApplication)getApplication()).getApplicationComponent())
                .build().inject(this);
    }



}
