package org.llin.demo.browserDOM.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user")
	public String userPage(@AuthenticationPrincipal Object principal, Model model) {
	    if (principal instanceof OAuth2User oAuth2User) {
	        model.addAttribute("username", oAuth2User.getAttribute("login"));
	    } else if (principal instanceof UserDetails userDetails) {
	        model.addAttribute("username", userDetails.getUsername());
	    } else {
	        model.addAttribute("username", "Anonymous");
	    }
	    return "user-page"; // user-page.html
	}
}
