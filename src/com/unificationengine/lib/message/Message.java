package com.unificationengine.lib.message;

/**
 * Created by deadlock on 4/5/16.
 */
public class Message {
    String subject;
    String body;
    String image;
    MessageLink link;

    public Message() {
    }

    public MessageLink getLink() {
        return link;
    }

    public void setLink(MessageLink link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
