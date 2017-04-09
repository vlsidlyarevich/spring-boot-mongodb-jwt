package com.github.vlsidlyarevich.security.service.impl;

import com.github.vlsidlyarevich.exception.model.UserNotFoundException;
import com.github.vlsidlyarevich.model.User;
import com.github.vlsidlyarevich.model.UserAuthentication;
import com.github.vlsidlyarevich.security.constants.SecurityConstants;
import com.github.vlsidlyarevich.security.service.TokenAuthenticationService;
import com.github.vlsidlyarevich.security.service.TokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("security.token.secret.key")
    private String secretKey;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.AUTH_HEADER_NAME);
        Jws<Claims> tokenData = parseToken(token);
        if (tokenData != null) {
            User user = getUserFromToken(tokenData);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    @Override
    public void addAuthentication(HttpServletResponse response, UserDetails userDetails) {
        final User user = (User) userDetails;
        response.addHeader(SecurityConstants.AUTH_HEADER_NAME, tokenService.getToken(user.getUsername(), user.getPassword()));
    }

    private Jws<Claims> parseToken(String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private User getUserFromToken(Jws<Claims> tokenData) {
        try {
            return (User) userDetailsService.loadUserByUsername(tokenData.getBody().get("username").toString());
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User " + tokenData.getBody().get("username").toString() + " not found");
        }
    }
}
