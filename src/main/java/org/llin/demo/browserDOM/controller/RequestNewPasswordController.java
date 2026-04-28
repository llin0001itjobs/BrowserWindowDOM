package org.llin.demo.browserDOM.controller;

import java.util.Optional;

import org.llin.demo.browserDOM.form.NewPasswordForm;
import org.llin.demo.browserDOM.form.RequestNewPasswordForm;
import org.llin.demo.browserDOM.model.User;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class RequestNewPasswordController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/requestNewPassword")
	public String requestNewPassword(@ModelAttribute("requestNewPasswordForm") RequestNewPasswordForm form) {
		return "page-set-new-password-request";
	}

	@PostMapping("/requestNewPassword")
	public String requestNewPasswordPost(@ModelAttribute("requestNewPasswordForm") RequestNewPasswordForm form,
	        @RequestParam(required = false) String cancel,
	        BindingResult result,
	        Model model,
	        RedirectAttributes redirectAttributes) {
		
		if ("true".equals(cancel)) {
			return "redirect:/login";
		}

		Optional<User> optUser = userRepository.findByUsername(form.getUserName());
		if (!optUser.isPresent()) {
			result.rejectValue("userName", "user.not.exists", "User does not exist.");
			return "page-set-new-password-request";
		}
	    NewPasswordForm npForm = new NewPasswordForm();
	    npForm.setUsername(optUser.get().getUsername());

	    // This is the key change
	    redirectAttributes.addFlashAttribute("newPasswordForm", npForm);
		return "redirect:/setNewPassword";
	}
	
	@GetMapping("/setNewPassword")
	public String setNewPassword(@ModelAttribute("newPasswordForm") NewPasswordForm form) {		
		return "page-set-new-password";
	}
	
	@PostMapping("/setNewPassword")
	public String setNewPasswordPost(@ModelAttribute("newPasswordForm") @Valid NewPasswordForm form, @RequestParam(required = false) String cancel, 
			BindingResult result, Model model) {
		System.out.println("cancel: " + cancel);
		if ("true".equals(cancel)) {
			return "redirect:/login";
		}
		
		// 1. Manual checks for password fields (you already have this)
		if (form.getNewPassword() == null || form.getNewPassword().trim().isEmpty()) {
			result.rejectValue("newPassword", "NotBlank", "New password must not be blank");
		}
		if (form.getConfirmPassword() == null || form.getConfirmPassword().trim().isEmpty()) {
			result.rejectValue("confirmPassword", "NotBlank", "Please confirm your password");
		}

		// 2. Custom password-match validation
		if (!result.hasErrors() && !form.getNewPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "password.mismatch", "The passwords do not match");
		}

		if (result.hasErrors()) {
			return "page-set-new-password";
		}
		
		Optional<User> optUser = userRepository.findByUsername(form.getUsername());
		User user = optUser.get();
		user.setPassword(passwordEncoder.encode(form.getNewPassword()));

		userRepository.save(user);
		System.out.println("SAVED = SAVED SAVED = SAVEDSAVED = SAVEDSAVED = SAVED");
		return "redirect:/login?passwordReset=true";		
	}
}
