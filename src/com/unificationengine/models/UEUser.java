package com.unificationengine.models;

import com.unificationengine.lib.UserKeychain;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEUser {

    private UserKeychain keychain;
    private String uri;

    public UEUser(UserKeychain keychain) {
        this.keychain = keychain;
    }


    public UEUser(String uri) {
        this.uri = uri;
    }


    public UserKeychain getKeychain() {
        return keychain;
    }

    public String getUri() {
        return uri;
    }
}
