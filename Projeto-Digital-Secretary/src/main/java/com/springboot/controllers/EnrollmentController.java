package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Student;
import com.springboot.services.EnrollmentService;
import com.springboot.services.StudentService;
import com.springboot.services.UserService;

@Controller
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/enrollment")
	public ModelAndView enrollmentStudent(@RequestParam Long id_discipline) {
		ModelAndView mav = new ModelAndView("principal/index");
		Student student = studentService.findByIdUser();
		
		enrollmentService.enrollmentStudent(student.getId(), id_discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Student successfully enrolled!");
		return mav;
	}
	
	@PostMapping(value = "/unenrollment")
	public ModelAndView unenrollmentStudent(@RequestParam Long id_discipline) {
		ModelAndView mav = new ModelAndView("principal/index");
		Student student = studentService.findByIdUser();
		
		enrollmentService.unenrollmentStudent(student.getId(), id_discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Student was successfully unenrolled from the discipline!");
		return mav;
	}
	
	
}
