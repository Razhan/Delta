package com.deltastudio.ran.delta.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.deltastudio.ran.delta.R;
import com.deltastudio.ran.deltalibrary.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ViewPager viewpager = (ViewPager)findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(TestFragment.newInstance("1"));
        fragmentList.add(TestFragment.newInstance("2"));
        fragmentList.add(TestFragment.newInstance("3"));
        fragmentList.add(TestFragment.newInstance("4"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewpager.setAdapter(new MyFragmentPagerAdapter(fragmentManager, fragmentList));
        viewpager.setPageTransformer(true, new ZoomOutSlideTransformer());
        indicator.setViewPager(viewpager);
    }

}
