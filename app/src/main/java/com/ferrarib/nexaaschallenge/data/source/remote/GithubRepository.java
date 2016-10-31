package com.ferrarib.nexaaschallenge.data.source.remote;

import com.ferrarib.nexaaschallenge.data.PullRequestsReponseWrapper;
import com.ferrarib.nexaaschallenge.data.RepositoriesResponseWrapper;
import com.ferrarib.nexaaschallenge.data.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bruno Ferrari on 20 October 2016.
 */

public interface GithubRepository {

    @GET("search/repositories")
    Call<RepositoriesResponseWrapper> getRepositories(@Query("q") String tag,
                                                      @Query("sort") String sort,
                                                      @Query("page") int page);

    @GET("repos/{owner}/{repository}/pulls")
    Call<PullRequestsReponseWrapper> getPullRequestsFromRepository(
                                            @Path("owner") String owner,
                                            @Path("repository") String repository);

}
