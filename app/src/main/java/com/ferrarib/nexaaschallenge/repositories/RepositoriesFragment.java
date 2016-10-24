package com.ferrarib.nexaaschallenge.repositories;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.ui.EndlessRecyclerViewScrollListener;
import com.ferrarib.nexaaschallenge.ui.RecyclerViewAdapterBase;
import com.ferrarib.nexaaschallenge.ui.adapter.RepositoriesAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_repositories)
public class RepositoriesFragment extends Fragment implements RepositoriesContract.View {

    @Bean RepositoriesPresenter mPresenter;
    @Bean RepositoriesAdapter mAdapter;

    @ViewById(R.id.repositories_recycler_view) RecyclerView mRecyclerView;

    @ViewById(R.id.repositories_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefresh;

    @ViewById(R.id.repositories_loading_layout) RelativeLayout mRepositoriesLoadingLayout;

    @AfterViews
    void init() {
        mPresenter.onFragmentLoad();
        setUpRecyclerView();
        setUpSwipeRefreshBehavior();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                mPresenter.retrieveRepositoriesList(page);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUpSwipeRefreshBehavior() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.retrieveRepositoriesList();
            }
        });
    }

    @Override
    public RecyclerViewAdapterBase getAdapter() {
        return mAdapter;
    }

    @Override
    public void hideRepositoriesProgressBar() {
        mRepositoriesLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void setRefreshingDone() {
        if (mSwipeRefresh.isRefreshing())
            mSwipeRefresh.setRefreshing(false);
        return;
    }

}
