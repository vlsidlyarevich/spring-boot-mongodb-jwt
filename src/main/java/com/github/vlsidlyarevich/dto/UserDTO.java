package com.github.vlsidlyarevich.dto;

import java.io.Serializable;


public class UserDTO implements Serializable {

    private static final long serialVersionUID = 91901774547107674L;

    private String username;
    private String password;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
