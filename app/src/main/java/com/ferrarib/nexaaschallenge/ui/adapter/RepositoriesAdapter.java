package com.ferrarib.nexaaschallenge.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.ferrarib.nexaaschallenge.data.Repository;
import com.ferrarib.nexaaschallenge.ui.RecyclerViewAdapterBase;
import com.ferrarib.nexaaschallenge.ui.RepositoryItemView;
import com.ferrarib.nexaaschallenge.ui.RepositoryItemView_;
import com.ferrarib.nexaaschallenge.ui.ViewWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Bruno Ferrari on 21 October 2016.
 */

@EBean
public class RepositoriesAdapter extends RecyclerViewAdapterBase<Repository, RepositoryItemView> {

    @RootContext Context mContext;

    @Override
    protected RepositoryItemView onCreateView(ViewGroup parent, int viewType) {
        return RepositoryItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<RepositoryItemView> holder, int position) {
        RepositoryItemView view = holder.getView();
        Repository repository = items.get(position);

        view.bind(repository);
    }
}
