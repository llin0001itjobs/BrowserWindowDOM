package org.llin.demo.browserDOM.config;

import org.llin.demo.browserDOM.service.CustomOAuth2UserService;
import org.llin.demo.browserDOM.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
	                                                   BCryptPasswordEncoder passwordEncoder,
	                                                   CustomUserDetailsService userDetailsService) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .userDetailsService(userDetailsService)
	            .passwordEncoder(passwordEncoder)
	            .and()
	            .build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService userDetailsService,
			CustomOAuth2UserService oAuth2UserService) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/login", "/css/**", "/js/**","/images/**").permitAll()
			    .requestMatchers("/user/**").authenticated()
			    .anyRequest().authenticated())	
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
				.oauth2Login(oauth -> oauth.loginPage("/login").defaultSuccessUrl("/home", true)
						.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService)))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
						.clearAuthentication(true));

		return http.build();
	}
}


