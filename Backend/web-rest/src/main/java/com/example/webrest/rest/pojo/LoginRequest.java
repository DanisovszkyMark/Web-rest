package com.example.webrest.rest.pojo;

import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.inject.Inject;
import javax.resource.spi.ConfigProperty;
import java.security.InvalidParameterException;

public class LoginRequest {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username.length() >= 3 && username.length() <= 50){
            this.username = username;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() >= 3 && password.length() <= 50){
            this.password = password;
        }
        else{
            throw new InvalidParameterException();
        }
    }
}
