package com.ferrarib.nexaaschallenge.pullrequests;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.data.PullRequest;
import com.ferrarib.nexaaschallenge.data.PullRequestsReponseWrapper;
import com.ferrarib.nexaaschallenge.data.source.GithubDataSource;
import com.ferrarib.nexaaschallenge.logger.Logger;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bruno Ferrari on 31 October 2016.
 */

@EBean
public class PullRequestsPresenter implements PullRequestsContract {

    private static final String TAG = PullRequestsPresenter.class.getSimpleName();
    private Bundle mBundle;

    @RootContext Context mContext;

    @Bean GithubDataSource mDataSource;

    @FragmentById(R.id.fragment_pull_request) PullRequestsFragment mFragment;

    @Override
    public void getPullRequests(String owner, String repository) {
        Call<PullRequestsReponseWrapper> call = mDataSource.getPullRequests(owner, repository);
        Log.d(TAG, "Request: " + call.request().url());
        call.enqueue(new Callback<PullRequestsReponseWrapper>() {
            @Override
            public void onResponse(Call<PullRequestsReponseWrapper> call,
                                   Response<PullRequestsReponseWrapper> response) {
                Logger.httpCodeLogger(TAG, response, call);
                List<PullRequest> items = response.body().getItems();
                for (PullRequest p : items) {
                    System.out.println(p);
                }
            }

            @Override
            public void onFailure(Call<PullRequestsReponseWrapper> call, Throwable t) {

            }
        });
    }

}
