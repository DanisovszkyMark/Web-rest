package com.example.webrest.rest.entity;

import javax.persistence.*;
import java.security.InvalidParameterException;

@Entity
@Table(name = "Tokens")
@NamedStoredProcedureQuery(
        name = "login",
        procedureName = "login",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_username"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "token")
        }
)
@NamedStoredProcedureQuery(
        name = "logout",
        procedureName = "logout",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_tokenKey"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "success")
        }
)
@NamedStoredProcedureQuery(
        name = "existsToken",
        procedureName = "existsToken",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_tokenKey"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "ex")
        }
)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String tokenKey;

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

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        if(tokenKey.length() == 32){
            this.tokenKey = tokenKey;
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
