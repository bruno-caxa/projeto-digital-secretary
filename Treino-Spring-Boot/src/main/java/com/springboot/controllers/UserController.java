package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Student;
import com.springboot.entities.Usuario;
import com.springboot.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/registerUser")
	public ModelAndView register(Usuario usuario) {
		ModelAndView mav = new ModelAndView("/principal/student/profile");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("student", new Student());
		userService.create(usuario);
		return mav;
	}
}
