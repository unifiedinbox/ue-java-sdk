package com.unificationengine.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.unificationengine.config.Constants;
import com.unificationengine.lib.ColorCodes;
import com.unificationengine.lib.Keychain;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by deadlock on 3/28/16.
 */
public class UERequest {

    public static boolean isValidJson(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    /**
     * fetches a resource
     *
     * @param resource RESTful resource. eg user/list
     * @param keyChain user:key combination
     * @param body     post body
     */
    public static JsonObject fetch(final String resource, Keychain keyChain, JsonObject requestBody) throws Exception {

        //Check if we have request body
        if (requestBody == null)
            requestBody = new JsonObject();

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(ColorCodes.YELLOW + "[REQ] => " + resource);
        System.out.println(ColorCodes.PURPLE + "[PAR] => ");
        System.out.println(requestBody.toString());
        HttpResponse<String> response = null;
        try {
            response = Unirest.post(Constants.API_BASE + resource)
                    .basicAuth(keyChain.getKey(), keyChain.getSecret())
                    .body(requestBody.toString())
                    .asString();
        } catch (UnirestException e) {
            throw e;
        }


        System.out.println(ColorCodes.BLUE + "[RES] => ");

        try {
            String jsonResponse = IOUtils.toString(response.getRawBody());
            if (isValidJson(jsonResponse)) {
                JsonObject jObj = new JsonParser().parse(jsonResponse).getAsJsonObject();
                System.out.println(gson.toJson(jObj) + ColorCodes.RESET);
                if (jObj.get("status").getAsInt() != 200) {
                    throw new Exception(jObj.get("info").getAsString());
                }
                return jObj;
            } else {
                System.out.println(ColorCodes.RED + jsonResponse + ColorCodes.RESET);
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

        return null;
    }
}
