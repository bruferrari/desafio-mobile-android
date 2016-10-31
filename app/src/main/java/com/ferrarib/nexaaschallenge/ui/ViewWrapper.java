package com.ferrarib.nexaaschallenge.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by Bruno Ferrari on 21 October 2016.
 */

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V mView;

    public ViewWrapper(V itemView) {
        super(itemView);
        mView = itemView;
    }

    public V getView() {
        return mView;
    }
}
