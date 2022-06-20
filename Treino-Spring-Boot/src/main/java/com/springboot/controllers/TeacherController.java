package com.springboot.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Discipline;
import com.springboot.entities.Teacher;
import com.springboot.entities.Usuario;
import com.springboot.services.TeacherService;
import com.springboot.services.UserService;

@Controller
public class TeacherController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(value = "/saveTeacher")
	public ModelAndView saveTeacher(Teacher student, Usuario user) {
		ModelAndView mav = new ModelAndView("principal/index");
		teacherService.save(student, user);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg","Teacher successfully saved!");
		return mav;
	}
	
	@GetMapping(value = "/teacherDisciplines")
	public ResponseEntity<Set<Discipline>> teacherDisciplines(@RequestParam Long id) {
		Set<Discipline> disciplines = teacherService.findById(id).getDisciplines();
		return new ResponseEntity<Set<Discipline>>(disciplines, HttpStatus.OK);
	}
}
