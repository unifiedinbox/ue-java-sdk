package com.unificationengine.lib.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by deadlock on 4/5/16.
 */
public class MessageOptions {

    private JsonArray receivers;
    private Message message;

    public MessageOptions() {
        receivers = new JsonArray();
    }

    public JsonArray getReceivers() {
        return receivers;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message m) {
        message = m;
    }

    public void addReceiver(String name) {
        JsonObject rec = new JsonObject();
        rec.addProperty("name", name);
        this.receivers.add(rec);
    }

    public void addReceiver(String name, String id) {
        JsonObject rec = new JsonObject();
        rec.addProperty("name", name);
        rec.addProperty("id", id);
        this.receivers.add(rec);
    }


}
