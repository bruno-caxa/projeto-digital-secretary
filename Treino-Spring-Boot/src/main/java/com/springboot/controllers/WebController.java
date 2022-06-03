package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Student;
import com.springboot.services.DisciplineService;
import com.springboot.services.EnrollmentService;
import com.springboot.services.StudentService;
import com.springboot.services.UserService;

@Controller
public class WebController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"/","/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		return "register";
	}
	
	@GetMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("principal/index");
		mav.addObject("user", userService.loadUserSession());
		return mav;
	}
	
	@GetMapping(value = "/addDiscipline")
	public ModelAndView addDiscipline() {
		ModelAndView mav = new ModelAndView("principal/admin/addDiscipline");
		mav.addObject("user", userService.loadUserSession());
		return mav;
	}
	
	@GetMapping(value = "/enrolledDisciplines")
	public ModelAndView enrolledDisciplines() {
		ModelAndView mav = new ModelAndView("principal/student/enrolledDisciplines");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("enrollments", enrollmentService.findByCpf(studentService.loadByIdUser().getCpf()));
		return mav;
	}
	
	@GetMapping(value = "/listDisciplines")
	public ModelAndView listDisciplines() {
		ModelAndView mav = new ModelAndView("principal/listDisciplines");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("disciplines", disciplineService.loadAllDisciplines());
		return mav;
	}
	
	@GetMapping(value = "/listStudents")
	public ModelAndView listStudents() {
		ModelAndView mav = new ModelAndView("principal/admin/listStudents");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("students", studentService.loadAllStudents());
		return mav;
	}
	
	@GetMapping(value = "/profile")
	public ModelAndView profile() {
		ModelAndView mav = new ModelAndView("principal/student/profile");
		mav.addObject("user", userService.loadUserSession());
		Student student = studentService.loadByIdUser();
		mav.addObject("student", student != null ? student : new Student());
		return mav;
	}
}
