package com.deltastudio.ran.delta.bb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.deltastudio.ran.delta.R;
import com.deltastudio.ran.deltalibrary.widget.bottombar.BottomBar;
import com.deltastudio.ran.deltalibrary.widget.bottombar.BottomBarTab;
import com.deltastudio.ran.deltalibrary.widget.fragment.DeltaFragmentManager;

public class Main2Activity extends AppCompatActivity {

    private static final int DEFAULT_POSITION = 0;

    private BottomBar mBottomBar;

    private DeltaFragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = new DeltaFragmentManager(getSupportFragmentManager(), new FragmentAdapter(), R.id.container);
        fragmentManager.setDefaultPosition(DEFAULT_POSITION);
        fragmentManager.onCreate(savedInstanceState);

        mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.setItems(new BottomBarTab(R.drawable.ic_favorites, "favorite"),
                            new BottomBarTab(R.drawable.ic_nearby, "nearby"),
                            new BottomBarTab(R.drawable.ic_recents, "recent"),
                            new BottomBarTab(R.drawable.ic_restaurants, "restaurant"));

        mBottomBar.setOnItemSelectedListener(pos -> {
            fragmentManager.showFragment(pos);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fragmentManager.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

}
