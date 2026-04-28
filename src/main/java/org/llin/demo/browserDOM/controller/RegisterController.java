package org.llin.demo.browserDOM.controller;

import java.util.UUID;

import org.llin.demo.browserDOM.model.User;
import org.llin.demo.browserDOM.repository.RoleRepository;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.llin.demo.browserDOM.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String showRegisterForm(@ModelAttribute("user") User user) {
		return "page-register";
	}

	@PostMapping("/register")
	public String handleRegistration(@ModelAttribute("user") @Valid User user, 
	                                 BindingResult result, 
	                                 Model model) {

	    // 1. Manual checks for password fields (you already have this)
	    if (user.getNewPassword() == null || user.getNewPassword().trim().isEmpty()) {
	        result.rejectValue("newPassword", "NotBlank", "New password must not be blank");
	    }
	    if (user.getConfirmPassword() == null || user.getConfirmPassword().trim().isEmpty()) {
	        result.rejectValue("confirmPassword", "NotBlank", "Please confirm your password");
	    }

	    // 2. Custom password-match validation
	    if (!result.hasErrors() && !user.getNewPassword().equals(user.getConfirmPassword())) {
	        result.rejectValue("confirmPassword", "password.mismatch", "The passwords do not match");
	    }

	    // === NEW: Duplicate username / email check ===
	    if (!result.hasErrors()) {   // only check if basic validation passed
	        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
	            result.rejectValue("username", "username.exists", "Username is already taken");
	        }
	        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	            result.rejectValue("email", "email.exists", "Email is already registered");
	        }
	    }

	    // 3. If ANY errors (including duplicates), show the form again
	    if (result.hasErrors()) {
	        model.addAttribute("message", "Please correct the errors below.");
	        return "page-register";
	    }

	    // 4. Everything is valid → proceed with registration (your existing code)
	    user.setPassword(passwordEncoder.encode(user.getNewPassword()));

	    // Clean transient fields
	    user.setNewPassword(null);
	    user.setConfirmPassword(null);

	    // Finish registration
	    user.setRole(roleRepository.findRoleByType("USER"));
	    user.setEnabled(false);
	    user.setEmailVerified(false);
	    user.setVerificationToken(UUID.randomUUID().toString());

	    userRepository.save(user);

	    // Send verification email (your existing code)
	    String verificationLink = "http://localhost:8080/browser-dom/verify?token=" + user.getVerificationToken();
	    String verificationText = "Please verify your email by clicking this link: " + verificationLink;

	    try {
	        emailService.sendHtmlEmail(user.getEmail(), "Verify Your Email",
	                emailService.convertToHtml(verificationText));
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }

	    return "redirect:/login?registrationSuccess=true";
	}
}
