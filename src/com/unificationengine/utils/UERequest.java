package com.unificationengine.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.unificationengine.config.Constants;
import com.unificationengine.lib.ColorCodes;
import com.unificationengine.lib.Keychain;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;

/**
 * Created by deadlock on 3/28/16.
 */
public class UERequest {

    /**
     * fetches a resource
     *
     * @param resource RESTful resource. eg user/list
     * @param keyChain user:key combination
     * @param body     post body
     */
    public static void fetch(final String resource, Keychain keyChain, JsonObject requestBody) {

        //Check if we have request body
        if (requestBody == null)
            requestBody = new JsonObject();

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(ColorCodes.YELLOW + "[REQ] => " + resource);
        System.out.println(Constants.API_BASE + resource);
        Unirest.post(Constants.API_BASE + resource)
                .basicAuth(keyChain.getKey(), keyChain.getSecret())
                .body(StringEscapeUtils.escapeJava(requestBody.toString()))
                .asJsonAsync(new Callback<JsonNode>() {
                    @Override
                    public void completed(HttpResponse<JsonNode> httpResponse) {
                        try {
                            System.out.println(httpResponse.getRawBody());
                            String jsonResponse = IOUtils.toString(httpResponse.getRawBody());
                            JsonObject jObj = new JsonParser().parse(jsonResponse).getAsJsonObject();
                            System.out.println(ColorCodes.BLUE + "[RES] => ");
                            System.out.println(gson.toJson(jObj) + ColorCodes.RESET);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(UnirestException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void cancelled() {
                        System.out.println("Request Cancelled");
                    }
                });
    }


}
