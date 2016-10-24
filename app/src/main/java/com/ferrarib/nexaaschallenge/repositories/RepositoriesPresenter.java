package com.ferrarib.nexaaschallenge.repositories;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.data.Repository;
import com.ferrarib.nexaaschallenge.data.ResponseWrapper;
import com.ferrarib.nexaaschallenge.data.source.GithubDataSource;
import com.ferrarib.nexaaschallenge.logger.Logger;
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

    private static final String TAG = RepositoriesPresenter.class.getSimpleName();
    private static final int FIRST_PAGE = 1;
    private List<Repository> mRepositories = new ArrayList<>();

    @Override
    public void onFragmentLoad() {
        retrieveRepositoriesList(FIRST_PAGE);
        //TODO: add another operations about loading fragment
    }

    @Override
    public void retrieveRepositoriesList(int page) {
        try {
            Call<ResponseWrapper> call = mDataSource.getRepositories(page);
            Log.d(TAG, "Request: " + call.request().url());
            call.enqueue(this);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    void retrieveRepositoriesList() {
        try {
            Call<ResponseWrapper> call = mDataSource.getRepositories(FIRST_PAGE);
            Log.d(TAG, "Request: " + call.request().url());
            call.enqueue(new Callback<ResponseWrapper>() {
                @Override
                public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                    List<Repository> items = response.body().getItems();
                    mFragment.hideRepositoriesProgressBar();
                    mFragment.setRefreshingDone();
                    mFragment.getAdapter().setItems(items);
                }

                @Override
                public void onFailure(Call<ResponseWrapper> call, Throwable t) {

                }
            });
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
        Logger.httpCodeLogger(TAG, response, call);
        mRepositories = response.body().getItems();
        mFragment.hideRepositoriesProgressBar();

        mFragment.getAdapter().updateItems(mRepositories);
    }

    @Override
    public void onFailure(Call<ResponseWrapper> call, Throwable t) {
        Log.d(TAG, t.getMessage());
    }
}
