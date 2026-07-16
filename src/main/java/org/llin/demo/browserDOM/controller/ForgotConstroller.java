package org.llin.demo.browserDOM.controller;

import java.util.Optional;

import org.llin.demo.browserDOM.entity.User;
import org.llin.demo.browserDOM.form.ForgotUserForm;
import org.llin.demo.browserDOM.repository.UserRepository;
import org.llin.demo.browserDOM.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
public class ForgotConstroller {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@GetMapping("/forgotUser")
	public String forgotUser(@ModelAttribute("forgotUserForm") ForgotUserForm form) {
		return "page-forgot-user";
	}

	@PostMapping("/forgotUser")
	public String forgotUserPost(@ModelAttribute("forgotUserForm") @Valid ForgotUserForm form,
			@RequestParam(required = false) String cancel, BindingResult result, Model model) {

		if ("true".equals(cancel)) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			return "page-forgot-user"; // redisplay form with errors
		}

		Optional<User> optUser = userRepository.findByEmail(form.getEmail());
		if (!optUser.isPresent()) {
			result.rejectValue("email", "email.not.exists", "Email does not exist.");
			return "page-forgot-user";
		}

		User user = optUser.get(); // ← fetch real user

		try {
			emailService.sendHtmlEmail(form.getEmail(), "This is your Username",
					emailService.convertToHtml("Username: " + user.getUsername()));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		model.addAttribute("message", "Username has been sent to your email.");
		return "page-forgot-user";

	}

}
