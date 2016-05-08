package com.unificationengine.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unificationengine.api.endpoints.MessageEndpoints;
import com.unificationengine.exceptions.UnificationEngineException;
import com.unificationengine.lib.message.Message;
import com.unificationengine.lib.message.MessageLink;
import com.unificationengine.lib.message.MessageOptions;
import com.unificationengine.utils.UERequest;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEConnection {
    private String uri;
    private String name;
    private String serviceToken;
    private String service;
    private UEUser user;

    public UEConnection(String name, String serviceToken, String service, UEUser user) {
        this.name = name;
        this.serviceToken = serviceToken;
        this.service = service;
        this.user = user;
        this.uri = String.format("%s://%s@%s.com", service, serviceToken, service);
    }

    public UEConnection(String name, String uri, UEUser user) throws UnificationEngineException {
        this.name = name;
        this.user = user;
        this.uri = uri;
        Pattern p = Pattern.compile("(.+?)://(.+?)@.+");
        Matcher m = p.matcher(uri);
        if (!m.find()) {
            throw new UnificationEngineException("Invalid Connection Uri");
        }
        this.service = m.group(1);
        this.serviceToken = m.group(2);
    }
    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }


    public String getServiceToken() {
        return serviceToken;
    }


    public String getService() {
        return service;
    }


    public UEUser getUser() {
        return user;
    }


    public JsonObject buildMessageBody(MessageOptions options) {
        final String DEFAULT_CONTENT_TYPE = "binary";
        JsonObject params = new JsonObject();
        JsonArray receivers = options.getReceivers();

        //Add this connector to receivers
        for (JsonElement receiver : receivers) {
//            System.out.println(receiver);
            receiver.getAsJsonObject().addProperty("Connector", this.name);
            if (receiver.getAsJsonObject().has("id")) {
                receiver.getAsJsonObject().addProperty("address", receiver.getAsJsonObject().get("id").getAsString());
            }
        }
        params.add("receivers", receivers);

        //Formulate Message
        JsonArray parts = new JsonArray();
        Message message = options.getMessage();

        //If message body exists
        if (message.getBody().length() > 0) {
            JsonObject bodyObj = new JsonObject();
            bodyObj.addProperty("id", "random");
            bodyObj.addProperty("contentType", DEFAULT_CONTENT_TYPE);
            bodyObj.addProperty("type", "body");
            bodyObj.addProperty("data", message.getBody());

            parts.add(bodyObj);
        }

        //If message image exists
        if (message.getImage().length() > 0) {
            JsonObject imageObj = new JsonObject();
            imageObj.addProperty("id", "random");
            imageObj.addProperty("contentType", DEFAULT_CONTENT_TYPE);
            imageObj.addProperty("type", "image_link");
            imageObj.addProperty("data", message.getImage());

            parts.add(imageObj);
        }


        //Link
        if (message.getLink() != null) {
            MessageLink link = message.getLink();
            if (link.getUri() != null) {
                JsonObject linkUriObj = new JsonObject();
                linkUriObj.addProperty("id", "random");
                linkUriObj.addProperty("contentType", DEFAULT_CONTENT_TYPE);
                linkUriObj.addProperty("type", "link");
                linkUriObj.addProperty("data", link.getUri());

                parts.add(linkUriObj);
            }

            if (link.getDesc() != null) {
                JsonObject linkDescObj = new JsonObject();
                linkDescObj.addProperty("id", "random");
                linkDescObj.addProperty("contentType", DEFAULT_CONTENT_TYPE);
                linkDescObj.addProperty("type", "link_description");
                linkDescObj.addProperty("data", link.getDesc());

                parts.add(linkDescObj);
            }

            if (link.getTitle() != null) {
                JsonObject linkTitleObj = new JsonObject();
                linkTitleObj.addProperty("id", "random");
                linkTitleObj.addProperty("contentType", DEFAULT_CONTENT_TYPE);
                linkTitleObj.addProperty("type", "link_title");
                linkTitleObj.addProperty("data", link.getTitle());

                parts.add(linkTitleObj);
            }


        }

        //If message subject exists
        if (message.getSubject().length() > 0) {
            params.addProperty("subject", message.getSubject());
        }


        params.add("parts", parts);

        return params;
    }


    public ArrayList<String> sendMessage(MessageOptions options) throws UnificationEngineException {
        JsonObject params = new JsonObject();
        params.add("message", this.buildMessageBody(options));
        JsonObject response = UERequest.fetch(MessageEndpoints.SEND, this.user.getKeychain(), params);
        return new Gson().fromJson(response.get("URIs").getAsJsonArray(), ArrayList.class);
    }
}
