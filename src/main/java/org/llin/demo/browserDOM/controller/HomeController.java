package org.llin.demo.browserDOM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/intro")
    public ModelAndView showInstruction() {
		ModelAndView modelAndView = new ModelAndView("home");		
		return modelAndView; 
    }

}