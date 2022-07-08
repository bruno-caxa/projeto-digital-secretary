package com.springboot.controllers;

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
import com.springboot.services.DisciplineService;
import com.springboot.services.TeacherService;
import com.springboot.services.UserService;

@Controller
public class DisciplineController {
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/deleteDiscipline")
	public ModelAndView deleteDiscipline(@RequestParam Long id_discipline) {
		ModelAndView mav = new ModelAndView("principal/index");
		disciplineService.delete(id_discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg","Discipline successfully deleted!");
		return mav;
	}
	
	@PostMapping(value = "/enrollTeacher")
	public ModelAndView enrollTeacher(Discipline discipline, Teacher teacher) {
		ModelAndView mav = new ModelAndView("principal/index");
		discipline.setTeacher(teacher);
		disciplineService.save(discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Teacher " + teacher.getName() + " enrolled in the course " + discipline.getName() + " successfully!");
		return mav;
	}
	
	@GetMapping(value = "/getDiscipline")
	public ResponseEntity<Discipline> getDiscipline(@RequestParam Long id) {
		Discipline discipline = disciplineService.findById(id);
		return new ResponseEntity<Discipline>(discipline, HttpStatus.OK);
	}
	
	@PostMapping(value = "/saveDiscipline")
	public ModelAndView saveDiscipline(Discipline discipline, @RequestParam Long id_teacher) {
		ModelAndView mav = new ModelAndView("principal/index");
		if(id_teacher != 0) {
			discipline.setTeacher(teacherService.findById(id_teacher));
		}
		disciplineService.save(discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Discipline successfully saved!");
		return mav;
	}
}
