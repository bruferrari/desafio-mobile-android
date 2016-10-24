package com.ferrarib.nexaaschallenge.repositories;

import com.ferrarib.nexaaschallenge.ui.RecyclerViewAdapterBase;

/**
 * Created by Bruno Ferrari on 20 October 2016.
 */

public interface RepositoriesContract {

    void onFragmentLoad();

    void retrieveRepositoriesList(int page);

    interface View {

        RecyclerViewAdapterBase getAdapter();

        void hideRepositoriesProgressBar();

        void setRefreshingDone();
    }

}
