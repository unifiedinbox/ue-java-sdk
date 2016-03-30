package com.unificationengine.models;

import com.unificationengine.lib.AppKeychain;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEApp {

    private AppKeychain keychain;

    public UEApp(AppKeychain keychain) {
        this.keychain = keychain;
    }

    public AppKeychain getKeychain() {
        return keychain;
    }

}
