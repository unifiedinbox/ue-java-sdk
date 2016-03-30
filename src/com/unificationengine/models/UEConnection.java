package com.unificationengine.models;

/**
 * Created by deadlock on 3/28/16.
 */
public class UEConnection {
    private String uri;
    private String name;
    private String serviceToken;
    private String service;
    private UEUser user;

    public UEConnection(String uri, String name, String serviceToken, String service, UEUser user) {
        this.uri = uri;
        this.name = name;
        this.serviceToken = serviceToken;
        this.service = service;
        this.user = user;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public void setServiceToken(String serviceToken) {
        this.serviceToken = serviceToken;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public UEUser getUser() {
        return user;
    }
}
