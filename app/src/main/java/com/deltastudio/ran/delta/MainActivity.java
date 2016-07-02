package com.deltastudio.ran.delta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.deltastudio.ran.delta.data.model.News;
import com.deltastudio.ran.delta.injector.components.DaggerNewsComponent;
import com.deltastudio.ran.delta.injector.components.NewsComponent;
import com.deltastudio.ran.delta.presenter.MainPresenter;
import com.deltastudio.ran.delta.view.MainView;
import com.deltastudio.ran.deltalibrary.presentation.ice.MvpLceActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpLceActivity<SwipeRefreshLayout, News, MainView, MainPresenter>
        implements MainView, SwipeRefreshLayout.OnRefreshListener {

    private NewsComponent newsComponent;
    private NewsAdapter adapter;
    @Inject
    ErrorMessageDeterminer errorMessageDeterminer;

    @BindView(R.id.recyclerView)    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    @Override
    protected void initDependencyInjector() {
        newsComponent = DaggerNewsComponent.builder()
                .applicationComponent(((NewsApplication) getApplication()).getApplicationComponent())
                .build();
        newsComponent.inject(this);
    }

    private void initLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        contentView.setOnRefreshListener(this);

        adapter = new NewsAdapter(this, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loadData(false);
    }

    @Override
    @NonNull
    public MainPresenter createPresenter() {
        return newsComponent.presenter();
    }

    @Override
    public void setData(News data) {
        adapter.set(data.getDatas());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getNews(pullToRefresh);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return errorMessageDeterminer.getErrorMessage(e, pullToRefresh);
    }

    @Override public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
