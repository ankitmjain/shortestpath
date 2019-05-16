package com.dhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShortestPathController {

	@RequestMapping("/")
    public String blankRedire(){
        return "redirect:/home";
	}
	
	@RequestMapping("/home")
    public String homePage(){
        return "index";
	}
	
}
