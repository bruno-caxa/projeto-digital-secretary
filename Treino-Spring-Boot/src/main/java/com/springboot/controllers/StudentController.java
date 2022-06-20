package com.springboot.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Enrollment;
import com.springboot.entities.Student;
import com.springboot.entities.Usuario;
import com.springboot.services.StudentService;
import com.springboot.services.UserService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/deleteStudent/{idStudent}")
	public ModelAndView deleteStudent(@PathVariable("idStudent") Long idStudent) {
		ModelAndView mav = new ModelAndView("principal/index");
		studentService.delete(idStudent);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg","Student successfully deleted!");
		return mav;
	}
	
	@PostMapping(value = "/saveStudent")
	public ModelAndView saveStudent(Student student, Usuario user) {
		ModelAndView mav = new ModelAndView("principal/index");
		studentService.save(student, user);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg","Student successfully saved!");
		return mav;
	}
	
	@GetMapping(value = "/studentEnrollments")
	public ResponseEntity<Set<Enrollment>> studentEnrollments(@RequestParam Long id) {
		Set<Enrollment> enrollments = studentService.findById(id).getEnrollments();
		return new ResponseEntity<Set<Enrollment>>(enrollments, HttpStatus.OK);
	}
}
