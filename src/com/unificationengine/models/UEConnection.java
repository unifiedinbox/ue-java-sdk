package com.unificationengine.models;

import com.unificationengine.exceptions.UnificationEngineException;

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


}
