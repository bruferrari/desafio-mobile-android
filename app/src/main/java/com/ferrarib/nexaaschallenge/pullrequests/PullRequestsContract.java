package com.ferrarib.nexaaschallenge.pullrequests;

/**
 * Created by Bruno Ferrari on 31 October 2016.
 */

public interface PullRequestsContract {

    void getPullRequests(String owner, String repository);

    interface View {

    }

}
