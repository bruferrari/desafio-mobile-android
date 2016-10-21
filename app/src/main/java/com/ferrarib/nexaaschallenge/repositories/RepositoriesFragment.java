package com.ferrarib.nexaaschallenge.repositories;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.ui.RecyclerViewAdapterBase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_repositories)
public class RepositoriesFragment extends Fragment implements RepositoriesContract.View {

    @Bean RepositoriesPresenter mPresenter;

    @ViewById(R.id.repositories_recycler_view) RecyclerView mRecyclerView;

    @AfterViews
    void init() {
        mPresenter.onFragmentLoad();
    }

    void setUpRecyclerView(RecyclerViewAdapterBase adapterBase) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(adapterBase);
    }
}
