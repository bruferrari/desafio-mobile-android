package com.ferrarib.nexaaschallenge.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bruno Ferrari on 20 October 2016.
 */

public class RepositoriesResponseWrapper extends ResponseWrapper {

    @SerializedName("total_count")
    private Long totalCount;

    private boolean incompleteResults;

    public Long getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<Repository> getItems() {
        return items;
    }

}
