package de.davitec.appdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Just return the login.html (server side rendering by themeleaf) to enable login with Spring Security
 *
 * @author rschueppel
 *
 */
@Controller
class LoginController {

	@RequestMapping("/login")
	String showLoginView(Model model) {
    	return "login";
    }
}
