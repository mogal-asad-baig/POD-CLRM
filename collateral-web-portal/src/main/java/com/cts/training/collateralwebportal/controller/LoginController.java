package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cts.training.collateralwebportal.model.LoginModel;
import com.cts.training.collateralwebportal.service.LoginService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class LoginController {

	private final static Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("model", new LoginModel());
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView performLogin(@Valid @ModelAttribute("model") LoginModel loginModel, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		log.info("BEGIN   -   [afterLogin()]");
		ModelAndView mv = new ModelAndView("login");
		try {
			if (result.hasErrors()) {
				return mv;
			}
			String token = loginService.login(loginModel);
			request.getSession().setAttribute("token", token);
			mv.setViewName("home");
			return mv;
		} catch (FeignException e) {
			// TODO: handle exception
			if (e.getMessage().contains("User name")) {
				model.addAttribute("status", "Invalid Credentials!!");
			} else if (e.getMessage().contains("Password is wrong")) {
				model.addAttribute("status", "Invalid Credentials!!");
			} else if (e.getMessage().contains("Invalid Credential")) {
				model.addAttribute("status", "Invalid Credentials!!");
			}
			return mv;
		}

	}
}
