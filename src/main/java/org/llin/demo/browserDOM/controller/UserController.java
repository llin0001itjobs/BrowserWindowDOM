package org.llin.demo.browserDOM.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // Retrieve the "login" attribute from the OAuth2User (GitHub's username)
            return "Hello, " + principal.getAttribute("login");
        } else {
            return "User not authenticated";
        }
    }
}
