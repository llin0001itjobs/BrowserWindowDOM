package org.llin.demo.browserDOM.service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.llin.demo.browserDOM.model.CustomOAuth2User;
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

		String userNameAttribute = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();

		Map<String, Object> attributes = oAuth2User.getAttributes();
		String provider = userRequest.getClientRegistration().getRegistrationId(); // e.g. "github", "google"

		// Attempt to extract email based on provider
		String email = extractEmail(attributes, provider);
		if (email == null) {
			throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
		}

		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setUsername(generateUsername(attributes, provider));
			user.setEmail(email);
			user.setPassword(UUID.randomUUID().toString()); // not used, but required
			user.setEnabled(false);
			user.setEmailVerified(false);
			user.setVerificationToken(UUID.randomUUID().toString());
			user.setRole(roleRepository.findRoleByName("ROLE_USER"));
			userRepository.save(user);
		}

		OAuth2User oUser = new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName())), attributes,
				userNameAttribute);

		return new CustomOAuth2User(oUser, user);
	}
	
	private String extractEmail(Map<String, Object> attributes, String provider) {
	    switch (provider.toLowerCase()) {
	        case "github":
	            return (String) attributes.getOrDefault("email", attributes.get("login") + "@github.com");
	        case "google":
	            return (String) attributes.get("email");
	        case "facebook":
	            return (String) attributes.get("email");
	        default:
	            return null;
	    }
	}
	
	private String generateUsername(Map<String, Object> attributes, String provider) {
	    switch (provider.toLowerCase()) {
	        case "github":
	            return (String) attributes.get("login"); // GitHub username
	        case "google":
	            return ((String) attributes.get("email")).split("@")[0]; // Local part of email
	        case "facebook":
	            Object nameObj = attributes.get("name");
	            if (nameObj != null) {
	                return nameObj.toString().toLowerCase().replaceAll("\\s+", ".");
	            }
	            break;
	        default:
	            return "user_" + UUID.randomUUID().toString().substring(0, 8);
	    }
	    return "user_" + UUID.randomUUID().toString().substring(0, 8);
	}
	
}
