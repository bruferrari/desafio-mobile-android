package com.ferrarib.nexaaschallenge.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bruno Ferrari on 31 October 2016.
 */
public class PullRequest {

    private String url;
    private Long id;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("diff_url")
    private String diffUrl;

    @SerializedName("patchUrl")
    private String patchUrl;

    @SerializedName("issueUrl")
    private String issueUrl;

    private int number;
    private String state;
    private String title;
    private UserWrapper user;
    private String body;

    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public UserWrapper getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }
}
