package com.example.webrest.rest.entity;

import javax.persistence.*;
import java.security.InvalidParameterException;
import org.apache.commons.validator.routines.EmailValidator;

@Entity
@Table(name = "Users")
@NamedStoredProcedureQuery(
        name = "updateUser",
        procedureName = "updateUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_username"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "success")
        }
)
@NamedStoredProcedureQuery(
        name = "updateUserWithoutPassword",
        procedureName = "UpdateUserWithoutPassword",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_username"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "success")
        }
)
@NamedStoredProcedureQuery(
        name = "deleteUser",
        procedureName = "deleteUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "success")
        }
)
@NamedStoredProcedureQuery(
        name = "blockUser",
        procedureName = "blockUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "success")
        }
)
@NamedStoredProcedureQuery(
        name = "activateUser",
        procedureName = "activateUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_activationKey"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "success")
        }
)
@NamedStoredProcedureQuery(
        name = "registration",
        procedureName = "registration",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_username"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "activationKey")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column()
    private boolean enabled;

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
        if(password.length() >= 3 && password.length() <= 50 ){
            this.password = password;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.length() >= 3 && email.length() <= 50 && EmailValidator.getInstance().isValid(email)){
            this.email = email;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
