//package com.deltastudio.ran.delta;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//import com.deltastudio.ran.delta.data.model.News;
//import com.deltastudio.ran.delta.injector.components.DaggerNewsComponent;
//import com.deltastudio.ran.delta.injector.components.NewsComponent;
//import com.deltastudio.ran.delta.injector.modules.NewsModule;
//import com.deltastudio.ran.delta.presenter.MainPresenter;
//import com.deltastudio.ran.delta.view.MainView;
//import com.deltastudio.ran.deltalibrary.presentation.ice.MvpLceActivity;
//import com.deltastudio.ran.deltalibrary.presentation.ice.MvpLceFragment;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class ReposFragment extends MvpLceFragment<SwipeRefreshLayout, News, MainView, MainPresenter>
//        implements MainView, SwipeRefreshLayout.OnRefreshListener {
//
//    private NewsComponent newsComponent;
//    private NewsAdapter adapter;
//
//    @BindView(R.id.recyclerView)    RecyclerView recyclerView;
//
//    private void injectDependencies() {
//        newsComponent = DaggerNewsComponent.builder()
//                .newsModule(new NewsModule(getContext()))
//                .build();
//        newsComponent.inject(this);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        injectDependencies();
//        return inflater.inflate(R.layout.fragment_repos, container, false);
//    }
//
//
//    @Override public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
//        adapter = new NewsAdapter(getContext(), null);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        contentView.setOnRefreshListener(this);
//    }
//
//    @Override
//    @NonNull
//    public MainPresenter createPresenter() {
//        return newsComponent.presenter();
//    }
//
//    @Override
//    public void setData(News data) {
//        adapter.set(data.getDatas());
//    }
//
//    @Override
//    public void loadData(boolean pullToRefresh) {
//        presenter.getNews(pullToRefresh);
//    }
//
//    @Override
//    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
//        return ErrorMessageDeterminer.getErrorMessage(e, pullToRefresh);
//    }
//
//    @Override
//    public void showError(Throwable e, boolean pullToRefresh) {
//        super.showError(e, pullToRefresh);
//        contentView.setRefreshing(false);
//    }
//
//    @Override
//    public void onRefresh() {
//        loadData(true);
//    }
//}
