package com.example.webrest.rest.pojo;

import org.apache.commons.validator.routines.EmailValidator;
import java.security.InvalidParameterException;

public class RegistrationRequest {

    private String username;

    private String email;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username.length() >= 3 && username.length() <= 50) {
            this.username = username;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.length() >= 3 && email.length() <= 150 && EmailValidator.getInstance().isValid(email)){
            this.email = email;
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
