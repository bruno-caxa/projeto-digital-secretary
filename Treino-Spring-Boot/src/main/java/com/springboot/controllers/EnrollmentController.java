package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entities.Enrollment;
import com.springboot.services.EnrollmentService;

@RestController
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping(value = "/enrollment")
	@ResponseBody
	public ResponseEntity<Enrollment> enrollmentStudent(@RequestParam(name = "id_student") Long id_student,
												  		@RequestParam(name = "id_course") Long id_course) {
		
		Enrollment enrollment = enrollmentService.enrollmentStudent(id_student, id_course);
		return new ResponseEntity<Enrollment>(enrollment, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listEnrollments")
	@ResponseBody
	public ResponseEntity<List<Enrollment>> listEnrollments() {
		List<Enrollment> enrollments = enrollmentService.findAll();
		
		return new ResponseEntity<List<Enrollment>>(enrollments, HttpStatus.OK);
	}
}
