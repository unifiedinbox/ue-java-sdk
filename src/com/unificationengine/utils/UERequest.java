package com.unificationengine.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.util.HashMap;

/**
 * Created by deadlock on 3/28/16.
 */
public class UERequest {

    public static void fetch(String resource, HashMap<String,String> parameters){

    }

    public static void main(String[] args) {
        Unirest.get("https://api.github.com").asJsonAsync(new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> httpResponse) {
                try {
                    System.out.println(IOUtils.toString(httpResponse.getRawBody()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(UnirestException e) {

            }

            @Override
            public void cancelled() {

            }
        });
    }
}
