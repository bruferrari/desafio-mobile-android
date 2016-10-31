package com.ferrarib.nexaaschallenge.pullrequests;

import android.support.v4.app.Fragment;

import com.ferrarib.nexaaschallenge.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Bruno Ferrari on 31 October 2016.
 */

@EFragment(R.layout.fragment_pull_requests)
public class PullRequestsFragment extends Fragment implements PullRequestsContract.View {

    @Bean PullRequestsPresenter mPullRequestsPresenter;

    @AfterViews
    void init() {

    }

}
