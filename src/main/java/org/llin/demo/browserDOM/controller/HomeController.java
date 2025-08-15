package org.llin.demo.browserDOM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login"; // Maps to login.html
    }
        
    @GetMapping("/home")
    public String home() {
        return "home"; // Maps to home.html
    }
    

} 
