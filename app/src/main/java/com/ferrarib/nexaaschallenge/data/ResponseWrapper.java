package com.ferrarib.nexaaschallenge.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno Ferrari on 31 October 2016.
 */
public abstract class ResponseWrapper {

    List<Repository> items = new ArrayList<>();

    List<PullRequest> pullRequests = new ArrayList<>();

}
