package com.ferrarib.nexaaschallenge.listing;

import android.util.Log;

import com.ferrarib.nexaaschallenge.data.ResponseWrapper;
import com.ferrarib.nexaaschallenge.data.Repository;
import com.ferrarib.nexaaschallenge.data.source.GithubDataSource;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
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
class ListingPresenter implements ListingContract, Callback<ResponseWrapper> {

    @RootContext ListingActivity mActivity;
    @Bean GithubDataSource mDataSource;

    private static final String TAG = ListingPresenter.class.getSimpleName();
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
        if (response.code() != 200)
            Log.d(TAG, response.code() + " " + call.request().url());
        else {
            Log.d(TAG, response.code() + " " + call.request().url());
            List<Repository> repositories = new ArrayList<>();

            if (response.body().getTotalCount() > 0) {
                System.out.println(response.body().getTotalCount());
                repositories = response.body().getItems();

                for (Repository r : repositories) {
                    Log.d(TAG, r.getName());
                }
            } else {
                System.out.println(response.body().getTotalCount());
                System.out.println(response.body().getItems().size());
            }
        }

    }

    @Override
    public void onFailure(Call<ResponseWrapper> call, Throwable t) {
        Log.d(TAG, t.getMessage());
    }
}
