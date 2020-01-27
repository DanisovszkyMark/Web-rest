package com.example.webrest.rest.dto;

import com.example.webrest.rest.entity.User;

public class UserDTO {

    private long id;

    private String username;

    private String email;

    private boolean enabled;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
    }
}
