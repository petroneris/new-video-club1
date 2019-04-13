package com.snezana.videoclub.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class for the Login activities.
 */
@Controller
public class LoginController {

	public static final String ATTR_USERNAME = "username";
	public static int paramPage = 0;

	/**
	 * Welcome page.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		return "welcome";
	}

	/**
	 * User home page.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		paramPage = 1;
		return "home";
	}

	/**
	 * Admin home page.
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		paramPage = 1;
		return "admin";
	}

	/**
	 * 'Access Denied' page. Shows every time when user attempts to access a page
	 * that (s)he is not authorized for.
	 */
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	/**
	 * Login page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	/**
	 * Logout page - successfully logout
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		paramPage = 0;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	/**
	 * Expired-url, every time when attempt multiple logins
	 */
	@RequestMapping(value = "/multiConcLoginsExp", method = RequestMethod.GET)
	public String alreadyLogin() {
		return "multiConcLoginsExp";
	}

	/**
	 * Get User information in controller from Spring Security
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}