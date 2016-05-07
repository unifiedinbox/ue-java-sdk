package com.unificationengine.utils;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.unificationengine.config.Constants;
import com.unificationengine.exceptions.UnificationEngineException;
import com.unificationengine.lib.ColorCodes;
import com.unificationengine.lib.Keychain;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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
    public static JsonObject fetch(final String resource, Keychain keyChain, JsonObject requestBody) throws UnificationEngineException {

        //Check if we have request body
        if (requestBody == null)
            requestBody = new JsonObject();

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(ColorCodes.YELLOW + "[REQ] => " + resource);
//        System.out.println(ColorCodes.PURPLE + "[PAR] => ");
//        System.out.println(requestBody.toString());
        HttpResponse<String> response = null;
        try {
            response = Unirest.post(Constants.API_BASE + resource)
                    .basicAuth(keyChain.getKey(), keyChain.getSecret())
                    .body(requestBody.toString())
                    .asString();
        } catch (UnirestException e) {
            throw new UnificationEngineException(e.getMessage());
        }


//        System.out.println(ColorCodes.BLUE + "[RES] => ");

        try {
            String jsonResponse = IOUtils.toString(response.getRawBody());
            if (isValidJson(jsonResponse)) {
                JsonObject jObj = new JsonParser().parse(jsonResponse).getAsJsonObject();
//                System.out.println(gson.toJson(jObj) + ColorCodes.RESET);

                if (jObj.has("status") && jObj.get("status").getAsInt() != 200) {
                    throw new UnificationEngineException(jObj.get("info").getAsString());

                }

                //For handling different response in message/send route
                if (jObj.has("Status")) {
                    ArrayList<String> errors = new ArrayList<String>();
//                    System.out.println(jObj.get("Status").getAsJsonObject().get(""));
                    for (Map.Entry<String, JsonElement> entry : jObj.get("Status").getAsJsonObject().entrySet()) {

                        if (entry.getValue().getAsJsonObject().get("status").getAsInt() != 200) {
                            errors.add(entry.getValue().getAsJsonObject().get("info").getAsString());
                        }
                    }

                    if (errors.size() > 0) {
                        throw new UnificationEngineException(Arrays.toString(errors.toArray()));
                    }

                }

                return jObj;
            } else {
                System.out.println(ColorCodes.RED + jsonResponse + ColorCodes.RESET);
            }
        } catch (IOException e) {
            throw new UnificationEngineException(e.getMessage());
        } catch (Exception e) {
            throw new UnificationEngineException(e.getMessage());
        }

        return null;
    }
}
