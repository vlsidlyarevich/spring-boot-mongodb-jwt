package com.github.vlsidlyarevich.dto;

import java.io.Serializable;


public class UserDTO implements Serializable {

    private String username;
    private String password;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
