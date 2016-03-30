package com.unificationengine.lib;

/**
 * Created by deadlock on 3/30/16.
 */
public class Keychain {
    private String key;
    private String secret;

    public Keychain(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }


}
