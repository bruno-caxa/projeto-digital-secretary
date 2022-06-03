package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Enrollment;
import com.springboot.entities.Student;
import com.springboot.services.EnrollmentService;
import com.springboot.services.StudentService;
import com.springboot.services.UserService;

@RestController
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/disciplinesEnrolled")
	public ResponseEntity<List<Enrollment>> disciplinesEnrolled(@RequestParam String cpf) {
		List<Enrollment> enrollments = enrollmentService.findByCpf(cpf);
		return new ResponseEntity<List<Enrollment>>(enrollments, HttpStatus.OK);
	}
	
	@GetMapping(value = "/enrollment/{id_discipline}")
	public ModelAndView enrollmentStudent(@PathVariable Long id_discipline) {
		ModelAndView mav = new ModelAndView("/principal/index");
		Student student = studentService.loadByIdUser();
		
		enrollmentService.enrollmentStudent(student.getId(), id_discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Student successfully enrolled!");
		return mav;
	}
	
	@GetMapping(value = "/unenrollment/{id_discipline}")
	public ModelAndView unenrollmentStudent(@PathVariable Long id_discipline) {
		ModelAndView mav = new ModelAndView("/principal/index");
		Student student = studentService.loadByIdUser();
		
		enrollmentService.unenrollmentStudent(student.getId(), id_discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Student was successfully unenrolled from the discipline!");
		return mav;
	}
	
	
}
