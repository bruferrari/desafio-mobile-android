package com.ferrarib.nexaaschallenge.data.source;

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
    public static final String BASE_URL = "https://github.com/";

    private GithubRepository githubRepository;

    public GithubDataSource() {
        githubRepository = ServiceFactory.createRetrofitService(GithubRepository.class, API_BASE_URL);
    }

    public Call<ResponseWrapper> getRepositories(int page) throws IOException {
        return githubRepository.getRepositories("language:Java", "stars", page);
    }
}
