package com.unificationengine.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unificationengine.api.endpoints.UserEndpoints;
import com.unificationengine.lib.AppKeychain;
import com.unificationengine.utils.UERequest;

import java.util.ArrayList;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEApp {

    private AppKeychain keychain;

    public UEApp(String appKey, String appSecret) {
        this.keychain = new AppKeychain(appKey, appSecret);
    }

    public AppKeychain getKeychain() {
        return keychain;
    }


    public UEUser createUser() {

        JsonObject response = null;
        try {
            response = UERequest.fetch(UserEndpoints.CREATE, this.keychain, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response.get("status").getAsInt() == 200) {

            String userUri = response.get("uri").getAsString();
            try {
                return new UEUser(userUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Boolean deleteUser(String userUri) {
        JsonObject params = new JsonObject();
        params.addProperty("uri", userUri);
        JsonObject response = null;
        try {
            response = UERequest.fetch(UserEndpoints.DELETE, this.keychain, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.get("status").getAsInt() == 200;

    }

    public Boolean deleteUser(UEUser user) {
        JsonObject params = new JsonObject();
        params.addProperty("uri", user.getUri());
        JsonObject response = null;
        try {
            response = UERequest.fetch(UserEndpoints.DELETE, this.keychain, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.get("status").getAsInt() == 200;

    }


    public ArrayList<String> listUsers() {
        JsonObject params = new JsonObject();
        JsonObject response = null;
        try {
            response = UERequest.fetch(UserEndpoints.LIST, this.keychain, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> userList = new ArrayList<String>();
        JsonArray userArray = response.getAsJsonArray("users");
        for (final JsonElement userEl : userArray) {
            JsonObject userObj = userEl.getAsJsonObject();
            try {
                userList.add(userObj.get("uri").getAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userList;

    }


}
