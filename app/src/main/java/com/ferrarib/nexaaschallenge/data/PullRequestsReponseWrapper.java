package com.ferrarib.nexaaschallenge.data;

import java.util.List;

/**
 * Created by Bruno Ferrari on 31 October 2016.
 */

public class PullRequestsReponseWrapper extends ResponseWrapper {

    public List<PullRequest> getItems() {
        return pullRequests;
    }
}
