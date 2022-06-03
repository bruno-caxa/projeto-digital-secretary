package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Student;
import com.springboot.services.StudentService;
import com.springboot.services.UserService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/saveStudent")
	public ModelAndView saveStudent(Student student) {
		ModelAndView mav = new ModelAndView("/principal/student/profile");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("student", student);
		studentService.save(student);
		return mav;
	}
}
