package com.unificationengine.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unificationengine.api.endpoints.ConnectionEndpoints;
import com.unificationengine.exceptions.UnificationEngineException;
import com.unificationengine.lib.UserKeychain;
import com.unificationengine.utils.UERequest;

import java.util.ArrayList;
import java.util.Map;
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


    public UEUser(String uri) throws UnificationEngineException {
        this.uri = uri;
        Pattern p = Pattern.compile("user://(.+?):(.+)@");
        Matcher m = p.matcher(uri);
        if (!m.find())
            throw new UnificationEngineException("Invalid User Uri");
        this.keychain = new UserKeychain(m.group(1), m.group(2));
    }

    public UserKeychain getKeychain() {
        return keychain;
    }

    public String getUri() {
        return uri;
    }



    public UEConnection addConnection(String conName, String conScheme, String accessToken) throws UnificationEngineException {
        String conUri = String.format("%s://%s@%s.com", conScheme, accessToken, conScheme);
        JsonObject params = new JsonObject();
        params.addProperty("name", conName);
        params.addProperty("uri", conUri);
        UERequest.fetch(ConnectionEndpoints.ADD, this.keychain, params);
        return new UEConnection(conName, accessToken, conScheme, this);
    }

    public Boolean removeConnection(String name) throws UnificationEngineException {
        JsonObject params = new JsonObject();
        params.addProperty("name", name);
        UERequest.fetch(ConnectionEndpoints.REMOVE, this.keychain, params);
        return true;
    }

    public Boolean removeConnection(UEConnection c) throws UnificationEngineException {
        this.removeConnection(c.getName());
        return true;
    }

    public ArrayList<UEConnection> listConnections() throws UnificationEngineException {
        ArrayList<UEConnection> connectionList = new ArrayList<UEConnection>();
        JsonObject response = UERequest.fetch(ConnectionEndpoints.LIST, this.keychain, new JsonObject());
        for (Map.Entry<String, JsonElement> key : response.get("connections").getAsJsonObject().entrySet()) {
            UEConnection con = new UEConnection(key.getKey(), key.getValue().getAsJsonObject().get("uri").getAsString(), this);
            connectionList.add(con);
        }
        return connectionList;
    }



    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
