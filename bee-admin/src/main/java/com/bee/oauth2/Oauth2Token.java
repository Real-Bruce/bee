package com.bee.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description token发放
 */
public class Oauth2Token implements AuthenticationToken {

    private String token;

    public Oauth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
