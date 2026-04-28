package org.llin.demo.browserDOM.controller;

import java.util.Optional;

import org.llin.demo.browserDOM.config.PropertyConfig;
import org.llin.demo.browserDOM.model.User;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.llin.demo.browserDOM.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;

@Controller
public class LoginController {

	@Autowired
	private PropertyConfig config;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	private String subjectVerified;

	private String textVerified;

	@PostConstruct
	private void init() {
		subjectVerified = config.getPropertyDefaultProperties().getApp().getMail().getSubject().getVerified();
		textVerified = config.getPropertyDefaultProperties().getApp().getMail().getText().getVerified();
	}

	@GetMapping("/login")
	public String handleLogin(@ModelAttribute("user") User user, @RequestParam(required = false) String register,
			@RequestParam(required = false) String verified, @RequestParam(required = false) String registrationSuccess,
			@RequestParam(required = false) String error, 
			@RequestParam(required = false) String passwordReset, Model model) {

		if (passwordReset != null) {
			model.addAttribute("message", "Password successfully reset.");
		}
		
		if (registrationSuccess != null) {
			model.addAttribute("message", "Registration successful. Please check your email to verify.");
		}

		if (verified != null) {
			model.addAttribute("message", "Email verified. Please log in.");
		}

		if (error != null) {
			model.addAttribute("message", "Invalid verification token.");
		}

		model.addAttribute("user", new User());
		model.addAttribute("register", register != null); // Still toggles form mode
		return "page-login";
	}


	@PostMapping("/setPassword")
	public String setPassword(@ModelAttribute("user") User user, @RequestParam(required = false) String success,
			@RequestParam(required = false) String error, Model model) {

		if (success != null) {
			model.addAttribute("message", "New password set successfully. Please check your email to verify.");
		}

		model.addAttribute("user", user);
		return "page-login";
	}

	@GetMapping("/verify")
	public String verifyEmail(@RequestParam("token") String token) {

		Optional<User> optUser = userRepository.findByVerificationToken(token);
		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setEnabled(true);
			user.setEmailVerified(true);
			user.setVerificationToken(null);
			userRepository.save(user);
			// Send confirmed email
			emailService.sendSimpleEmail(user.getEmail(), subjectVerified, textVerified);

			return "redirect:/login?verified=true";
		}
		return "redirect:/login?error=invalidToken";
	}

}
