package org.llin.demo.browserDOM.service;

import java.util.Collections;
import java.util.Optional;

import org.llin.demo.browserDOM.entity.Role;
import org.llin.demo.browserDOM.entity.User;
import org.llin.demo.browserDOM.repository.RoleRepository;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) {
    	Optional<User> optUser = userRepository.findByUsername(username);
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
        	optUser.get().getUsername(),
        	optUser.get().getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(getDefaultRole().getType()))
        );
    }
    

    public User findUserByUsername(String username) {
    	Optional<User> optUser = userRepository.findByUsername(username);
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return optUser.get();
    }    
    
    public User findUserByEmail(String email) {
    	Optional<User> optUser = userRepository.findByEmail(email);
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + email);
        } else

        return optUser.get();
    }
    
	private Role getDefaultRole() {
        Optional<Role> optRole = roleRepository.findRoleByType("USER");
        if (optRole.isEmpty()) {
            throw new OAuth2AuthenticationException(
                "Default role 'USER' not found in database. " +
                "Check that RoleSeeder has run or manually insert the role.");
        }
        
        return optRole.get();
	}    
}    