package org.llin.demo.browserDOM.config;

import org.llin.demo.browserDOM.service.CustomOAuth2UserService;
import org.llin.demo.browserDOM.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, CustomUserDetailsService userDetailsService)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService userDetailsService,
			CustomOAuth2UserService oAuth2UserService) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/login", "/register", "/css/**", "/js/**")
				.permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
				.oauth2Login(oauth -> oauth.loginPage("/login").defaultSuccessUrl("/home", true)
						.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService)))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
						.clearAuthentication(true))
				.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
