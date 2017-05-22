package com.github.vlsidlyarevich.dto;

import java.io.Serializable;


public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 6710061358371752955L;

    private String token;

    public TokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
