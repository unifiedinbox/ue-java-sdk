package com.unificationengine.models;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEApp {

    private String appKey;
    private String appSecret;

    public UEApp(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
