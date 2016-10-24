package com.ferrarib.nexaaschallenge.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno Ferrari on 21 October 2016.
 */

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items = new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateView(parent, viewType));
    }

    protected abstract V onCreateView(ViewGroup parent, int viewType);

    //TODO: add methods to manipulate items

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void updateItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void refreshItems(List<T> items) {
        items.clear();
        this.items = items;
        notifyDataSetChanged();
    }
}
