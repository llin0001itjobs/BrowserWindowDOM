package org.llin.demo.browserDOM.controller;

import org.llin.demo.browserDOM.service.DownloadsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	DownloadsProvider downloadProvider;
	
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
        
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @GetMapping("/home/main")
    public String main(HttpSession session) {
    	session.setAttribute("DownloadPath",downloadProvider.getDownloadsPath());
        return "main";
    }
    
} 
