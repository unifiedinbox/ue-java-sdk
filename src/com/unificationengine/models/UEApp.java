package com.unificationengine.models;

import com.google.gson.JsonObject;
import com.unificationengine.api.endpoints.UserEndpoints;
import com.unificationengine.lib.AppKeychain;
import com.unificationengine.utils.UERequest;

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

        JsonObject response = UERequest.fetch(UserEndpoints.CREATE, this.keychain, null);

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
        JsonObject response = UERequest.fetch(UserEndpoints.DELETE, this.keychain, params);
        return response.get("status").getAsInt() == 200;

    }

    public Boolean deleteUser(UEUser user) {
        JsonObject params = new JsonObject();
        params.addProperty("uri", user.getUri());
        JsonObject response = UERequest.fetch(UserEndpoints.DELETE, this.keychain, params);
        return response.get("status").getAsInt() == 200;

    }

}
