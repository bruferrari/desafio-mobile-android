package com.ferrarib.nexaaschallenge.ui;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.data.Repository;
import com.ferrarib.nexaaschallenge.data.source.GithubDataSource;
import com.ferrarib.nexaaschallenge.pullrequests.PullRequestsActivity_;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bruno Ferrari on 21 October 2016.
 */

@EViewGroup(R.layout.repositories_row)
public class RepositoryItemView extends RelativeLayout implements View.OnClickListener {

    private static final String TAG = RepositoryItemView.class.getSimpleName();

    private Context mContext;

    @ViewById(R.id.repository_name) TextView mRepositoryName;

    @ViewById(R.id.repository_description) TextView mRepositoryDescription;

    @ViewById(R.id.username) TextView mUsername;

    @ViewById(R.id.name_surname) TextView mFullName;

    @ViewById(R.id.user_image) ImageView mUserImage;

    @ViewById(R.id.forks_quantity) TextView mForksQuantity;

    @ViewById(R.id.starred_times) TextView mStarredTimes;

    @ViewById(R.id.user_image_progress_bar) ProgressBar mUserImgProgressBar;

    private Repository mRepository;

    public RepositoryItemView(Context context) {
        super(context);
        mContext = context;
    }

    public void bind(Repository repository) {
        mRepository = repository;
        mRepositoryName.setText(repository.getName());
        mRepositoryDescription.setText(repository.getDescription());
        mUsername.setText(repository.getOwner().getLogin());
        mFullName.setText(repository.getFullName());
        Picasso.with(mContext)
                .load(GithubDataSource.BASE_URL + repository.getOwner().getLogin() + ".png?size=60")
                .fit()
                .transform(new RoundTransform())
                .into(mUserImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mUserImgProgressBar.setVisibility(GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        mForksQuantity.setText(formatNumber(repository.getForksQuantity()));
        mStarredTimes.setText(formatNumber(repository.getStargazersCount()));
        this.setOnClickListener(this);
    }

    private String formatNumber(Long number) {
        DecimalFormat decimalFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            decimalFormat = new DecimalFormat("###,###,###");
            return decimalFormat.format(number);
        } else {
            return String.valueOf(number);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "owner: " + mRepository.getOwner().getLogin()
                + "\nrepo: " + mRepository.getName());
        Bundle bundle = new Bundle();
        bundle.putString("owner", mRepository.getOwner().getLogin());
        bundle.putString("repository", mRepository.getName());

        PullRequestsActivity_.intent(mContext).extra("bundle", bundle).start();
    }
}
