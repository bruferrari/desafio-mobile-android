package com.ferrarib.nexaaschallenge.repositories;

import android.util.Log;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.data.Repository;
import com.ferrarib.nexaaschallenge.data.RepositoriesResponseWrapper;
import com.ferrarib.nexaaschallenge.data.source.GithubDataSource;
import com.ferrarib.nexaaschallenge.logger.Logger;

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
class RepositoriesPresenter implements RepositoriesContract, Callback<RepositoriesResponseWrapper> {

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
            Call<RepositoriesResponseWrapper> call = mDataSource.getRepositories(page);
            Log.d(TAG, "Request: " + call.request().url());
            call.enqueue(this);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    void retrieveRepositoriesList() {
        try {
            Call<RepositoriesResponseWrapper> call = mDataSource.getRepositories(FIRST_PAGE);
            Log.d(TAG, "Request: " + call.request().url());
            call.enqueue(new Callback<RepositoriesResponseWrapper>() {
                @Override
                public void onResponse(Call<RepositoriesResponseWrapper> call, Response<RepositoriesResponseWrapper> response) {
                    Logger.httpCodeLogger(TAG, response, call);
                    List<Repository> items = response.body().getItems();
                    mFragment.hideRepositoriesProgressBar();
                    mFragment.setRefreshingDone();
                    mFragment.getAdapter().setItems(items);
                }

                @Override
                public void onFailure(Call<RepositoriesResponseWrapper> call, Throwable t) {

                }
            });
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onResponse(Call<RepositoriesResponseWrapper> call, Response<RepositoriesResponseWrapper> response) {
        Logger.httpCodeLogger(TAG, response, call);
        mRepositories = response.body().getItems();
        mFragment.hideRepositoriesProgressBar();

        mFragment.getAdapter().updateItems(mRepositories);
    }

    @Override
    public void onFailure(Call<RepositoriesResponseWrapper> call, Throwable t) {
        Log.e(TAG, t.getMessage());
    }
}
