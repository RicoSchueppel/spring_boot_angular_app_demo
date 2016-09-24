package de.davitec.appdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {

	@PreAuthorize("hasRole('USER')")
	@RequestMapping("/")
	String getHome(Model model) {
		model.addAttribute("message", "I'm an important message!");
    	return "index";
    }
}