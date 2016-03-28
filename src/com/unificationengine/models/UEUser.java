package com.unificationengine.models;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEUser {

    private String userKey;
    private String userSecret;
    private String uri;

    public UEUser(String userKey, String userSecret) {
        this.userKey = userKey;
        this.userSecret = userSecret;
    }

    public UEUser(String uri) {
        this.uri = uri;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public String getUri() {
        return uri;
    }
}
