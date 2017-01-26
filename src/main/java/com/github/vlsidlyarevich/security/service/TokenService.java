package com.github.vlsidlyarevich.security.service;


public interface TokenService {

    String getToken(String username, String password);
}
