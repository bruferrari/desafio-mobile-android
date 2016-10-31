package com.ferrarib.nexaaschallenge.data.source;

import com.ferrarib.nexaaschallenge.data.PullRequestsReponseWrapper;
import com.ferrarib.nexaaschallenge.data.RepositoriesResponseWrapper;
import com.ferrarib.nexaaschallenge.data.ResponseWrapper;
import com.ferrarib.nexaaschallenge.data.source.remote.GithubRepository;
import com.ferrarib.nexaaschallenge.data.source.remote.ServiceFactory;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by Bruno Ferrari on 20 October 2016.
 */

@EBean
public class GithubDataSource {

    private static final String API_BASE_URL = "https://api.github.com/";
    private static final String JAVA_QUERY = "language:Java";
    private static final String SORT_STARS = "stars";

    public static final String BASE_URL = "https://github.com/";

    private GithubRepository githubRepository;

    public GithubDataSource() {
        githubRepository = ServiceFactory.createRetrofitService(GithubRepository.class, API_BASE_URL);
    }

    public Call<RepositoriesResponseWrapper> getRepositories(int page) throws IOException {
        return githubRepository.getRepositories(JAVA_QUERY, SORT_STARS, page);
    }

    public Call<PullRequestsReponseWrapper> getPullRequests(String owner, String repository) {
        return githubRepository.getPullRequestsFromRepository(owner, repository);
    }
}
