package org.llin.demo.browserDOM.controller;

import java.util.UUID;

import org.llin.demo.browserDOM.model.User;
import org.llin.demo.browserDOM.repository.RoleRepository;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String register,
                                @RequestParam(required = false) String verified,
                                Model model) {
        if (register != null)
            model.addAttribute("message", "Registration successful. You can now log in.");
        if (verified != null)
            model.addAttribute("message", "Email verified. Please log in.");

        model.addAttribute("user", new User());
        model.addAttribute("register", register != null); // toggle email field
        return "login-page";
    }

    @PostMapping("/register")
    public String handleRegistration(@ModelAttribute("user") @Valid User user,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "login-page";
        }

        // Optional: check if username/email already exist
        if (userRepository.findByUsername(user.getUsername()) != null) {
            result.rejectValue("username", "error.user", "Username already taken");
            model.addAttribute("register", true);
            return "login-page";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findRoleByName("ROLE_USER"));
        user.setEnabled(false);
        user.setEmailVerified(false);
        user.setVerificationToken(UUID.randomUUID().toString());
        userRepository.save(user);

        // Send verification email here...

        return "redirect:/login?register=true";
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            user.setEnabled(true);
            user.setEmailVerified(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return "redirect:/login?verified=true";
        }
        return "redirect:/login?error=invalidToken";
    }
}
