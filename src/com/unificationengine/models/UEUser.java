package com.unificationengine.models;

import com.google.gson.Gson;
import com.unificationengine.lib.UserKeychain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEUser {

    private UserKeychain keychain;
    private String uri;

    public UEUser(String userKey, String userSecret) {
        this.keychain = new UserKeychain(userKey, userSecret);
    }


    public UEUser(String uri) throws Exception {
        this.uri = uri;
        Pattern p = Pattern.compile("user://(.+?):(.+)@");
        Matcher m = p.matcher(uri);
        if (!m.find())
            throw new Exception("Invalid User Uri");
        this.keychain = new UserKeychain(m.group(1), m.group(2));
    }


    public UserKeychain getKeychain() {
        return keychain;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
