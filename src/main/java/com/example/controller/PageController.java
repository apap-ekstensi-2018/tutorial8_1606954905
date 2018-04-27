package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/")
	public String index () {
	 return "index";
	}
	@RequestMapping("/login")
	public String login () {
	 return "login";
	}
	
	 
}
