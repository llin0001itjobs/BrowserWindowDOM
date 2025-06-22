package org.llin.demo.browserDOM.service;

import java.util.Collections;

import org.llin.demo.browserDOM.model.User;
import org.llin.demo.browserDOM.repository.RoleRepository;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email"); // GitHub provides email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // Create a new user if not found
            user = new User();
            user.setUsername(oAuth2User.getAttribute("login")); // GitHub username
            user.setEmail(email);
            user.setPassword(""); // No password for OAuth2 users
            user.setEnabled(true);
            user.setRole(roleRepository.findRoleByName("ROLE_USER")); // Default role
            userRepository.save(user);
        }
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName())),
                oAuth2User.getAttributes(),
                "email" // Attribute to use as the principal name
        );
    }
}
