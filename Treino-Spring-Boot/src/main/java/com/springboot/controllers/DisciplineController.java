package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Discipline;
import com.springboot.repositories.DisciplineRepository;
import com.springboot.services.UserService;

@Controller
public class DisciplineController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DisciplineRepository disciplineRepository;
	
	@PostMapping(value = "/createDiscipline")
	public ModelAndView createDiscipline(Discipline discipline) {
		ModelAndView mav = new ModelAndView("/principal/index");
		disciplineRepository.save(discipline);
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("msg", "Discipline created successfull!");
		return mav;
	}
}
