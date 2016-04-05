package com.unificationengine.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unificationengine.api.endpoints.UserEndpoints;
import com.unificationengine.exceptions.UnificationEngineException;
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


    public UEUser createUser() throws UnificationEngineException {
        JsonObject response = UERequest.fetch(UserEndpoints.CREATE, this.keychain, null);
        String userUri = response.get("uri").getAsString();
        return new UEUser(userUri);
    }


    public Boolean deleteUser(String userUri) throws UnificationEngineException {
        JsonObject params = new JsonObject();
        params.addProperty("uri", userUri);
        JsonObject response = null;
        response = UERequest.fetch(UserEndpoints.DELETE, this.keychain, params);
        return true;
    }

    public Boolean deleteUser(UEUser user) throws UnificationEngineException {
        JsonObject params = new JsonObject();
        params.addProperty("uri", user.getUri());
        JsonObject response = null;
        response = UERequest.fetch(UserEndpoints.DELETE, this.keychain, params);
        return true;
    }


    public ArrayList<String> listUsers() throws UnificationEngineException {
        JsonObject params = new JsonObject();
        JsonObject response = null;
        response = UERequest.fetch(UserEndpoints.LIST, this.keychain, params);
        ArrayList<String> userList = new ArrayList<String>();
        JsonArray userArray = response.getAsJsonArray("users");
        for (final JsonElement userEl : userArray) {
            JsonObject userObj = userEl.getAsJsonObject();
            userList.add(userObj.get("uri").getAsString());
        }
        return userList;
    }


}
