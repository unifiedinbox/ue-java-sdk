package com.unificationengine.lib.message;

/**
 * Created by deadlock on 4/5/16.
 */
public class MessageLink {
    String uri;
    String desc;
    String title;

    public MessageLink() {
    }

    public MessageLink(String uri, String desc, String title) {
        this.uri = uri;
        this.desc = desc;
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
