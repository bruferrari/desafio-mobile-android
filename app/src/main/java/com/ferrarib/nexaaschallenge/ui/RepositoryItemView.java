package com.ferrarib.nexaaschallenge.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ferrarib.nexaaschallenge.R;
import com.ferrarib.nexaaschallenge.data.Repository;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bruno Ferrari on 21 October 2016.
 */

@EViewGroup(R.layout.repositories_row)
public class RepositoryItemView extends RelativeLayout {

    @ViewById(R.id.repository_name) TextView mRepositoryName;

    @ViewById(R.id.repository_description) TextView mRepositoryDescription;

    @ViewById(R.id.username) TextView mUsername;

    @ViewById(R.id.name_surname) TextView mFullName;

    @ViewById(R.id.user_image) ImageView mUserImage;

    public RepositoryItemView(Context context) {
        super(context);
    }

    public void bind(Repository repository) {
        mRepositoryName.setText(repository.getName());
        mRepositoryDescription.setText(repository.getDescription());
        mUsername.setText(repository.getOwner().getLogin());
        mFullName.setText(repository.getFullName());
    }
}
