package com.ferrarib.nexaaschallenge.repositories;

import android.util.Log;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.data.Repository;
import com.ferrarib.nexaaschallenge.data.ResponseWrapper;
import com.ferrarib.nexaaschallenge.data.source.GithubDataSource;
import com.ferrarib.nexaaschallenge.ui.adapter.RepositoriesAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.RootContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bruno Ferrari on 20 October 2016.
 */

@EBean
class RepositoriesPresenter implements RepositoriesContract, Callback<ResponseWrapper> {

    @RootContext RepositoriesActivity mActivity;

    @FragmentById(R.id.fragment) RepositoriesFragment mFragment;

    @Bean GithubDataSource mDataSource;
    @Bean RepositoriesAdapter mAdapter;

    private static final String TAG = RepositoriesPresenter.class.getSimpleName();
    private List<Repository> mRepositories = new ArrayList<>();

    @Override
    public void onFragmentLoad() {
        try {
            Call<ResponseWrapper> call = mDataSource.getRepositories();
            call.enqueue(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
        if (response.code() != 200) {
            Log.e(TAG, "Error while requesting "
                    + call.request().url() + " with HTTP CODE " + response.code());
        }
        mRepositories = response.body().getItems();

        mAdapter.setItems(mRepositories);
        mFragment.setUpRecyclerView(mAdapter);
    }

    @Override
    public void onFailure(Call<ResponseWrapper> call, Throwable t) {
        Log.d(TAG, t.getMessage());
    }
}
