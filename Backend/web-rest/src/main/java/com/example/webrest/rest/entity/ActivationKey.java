package com.example.webrest.rest.entity;

import javax.persistence.*;
import java.security.InvalidParameterException;

//nem használt entity

@Entity
public class ActivationKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String activationKey;

    @Column(nullable = false)
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id > 0){
            this.id = id;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        if(activationKey.length() == 12){
            this.activationKey = activationKey;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        if(userId > 0){
            this.userId = userId;
        }
        else{
            throw new InvalidParameterException();
        }
    }
}
