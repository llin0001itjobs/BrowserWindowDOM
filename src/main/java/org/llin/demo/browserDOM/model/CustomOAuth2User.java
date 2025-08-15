package org.llin.demo.browserDOM.model;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oAuth2User;
    private User user;

    public CustomOAuth2User(OAuth2User oAuth2User, User user) {
        this.oAuth2User = oAuth2User;
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }
    
    @Override
    public String toString() {
        return "CustomOAuth2User{username=" + user.getUsername() + ", authorities=" + getAuthorities() + "}";
    }
}
