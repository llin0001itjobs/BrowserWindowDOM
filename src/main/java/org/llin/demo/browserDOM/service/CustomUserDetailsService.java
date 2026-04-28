package org.llin.demo.browserDOM.service;

import java.util.Collections;
import java.util.Optional;

import org.llin.demo.browserDOM.model.User;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

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
            Collections.singletonList(new SimpleGrantedAuthority(optUser.get().getRole().getType()))
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
}    