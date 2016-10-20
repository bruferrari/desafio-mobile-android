package com.ferrarib.nexaaschallenge.listing;

import android.support.v4.app.Fragment;

import com.ferrarib.nexaaschallenge.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_main)
public class ListFragment extends Fragment implements ListingContract.View {

    @Bean ListingPresenter mPresenter;

    @AfterViews
    void init() {
        mPresenter.onFragmentLoad();
    }
}
