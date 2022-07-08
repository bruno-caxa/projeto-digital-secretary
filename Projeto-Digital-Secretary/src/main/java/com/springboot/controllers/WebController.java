package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Discipline;
import com.springboot.entities.Student;
import com.springboot.entities.Teacher;
import com.springboot.entities.Usuario;
import com.springboot.services.DisciplineService;
import com.springboot.services.StudentService;
import com.springboot.services.TeacherService;
import com.springboot.services.UserService;

@Controller
public class WebController {

	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
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

	@GetMapping(value = "/addStudent")
	public ModelAndView addStudent() {
		ModelAndView mav = new ModelAndView("principal/admin/addStudent");
		mav.addObject("user", userService.loadUserSession());
		return mav;
	}
	
	@GetMapping(value = "/addTeacher")
	public ModelAndView addTeacher() {
		ModelAndView mav = new ModelAndView("principal/admin/addTeacher");
		mav.addObject("user", userService.loadUserSession());
		return mav;
	}
	
	@GetMapping(value = "/enrolledDisciplineStudent")
	public ModelAndView enrolledDisciplineStudent() {
		ModelAndView mav = new ModelAndView("principal/student/enrolledDisciplines");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("enrollments", studentService.findEnrollments());
		return mav;
	}
	
	@GetMapping(value = "/enrolledDisciplineTeacher")
	public ModelAndView enrolledDisciplineTeacher() {
		ModelAndView mav = new ModelAndView("principal/teacher/enrolledDisciplines");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("disciplines", teacherService.findDisciplines());
		return mav;
	}
	
	@GetMapping(value = "/enrollTeacherDiscipline")
	public ModelAndView enrollTeacher() {
		ModelAndView mav = new ModelAndView("principal/admin/enrollTeacher");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("disciplines", disciplineService.findDisciplinesWithoutTeacher());
		mav.addObject("teachers", teacherService.findAllTeachers());
		return mav;
	}
	
	@GetMapping(value = "/listDisciplines")
	public ModelAndView listDisciplines() {
		ModelAndView mav = new ModelAndView("principal/listDisciplines");
		Usuario user =  userService.loadUserSession();
		List<Discipline> disciplines = user.getAuthorities()
										   .stream()
										   .anyMatch(r -> r.getAuthority().equals("ROLE_STUDENT")) 
										   ?disciplineService.findDisciplinesIsNotEnrolled() 
										   :disciplineService.findAll();
		mav.addObject("user", user);
		mav.addObject("disciplines", disciplines);
		mav.addObject("teachers", teacherService.findAllTeachers());
		return mav;
	}
	
	@GetMapping(value = "/listStudents")
	public ModelAndView listStudents() {
		ModelAndView mav = new ModelAndView("principal/admin/listStudents");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("students", studentService.findAllStudents());
		return mav;
	}
	
	@GetMapping(value = "/listTeachers")
	public ModelAndView listTeachers() {
		ModelAndView mav = new ModelAndView("principal/admin/listTeachers");
		mav.addObject("user", userService.loadUserSession());
		mav.addObject("teachers", teacherService.findAllTeachers());
		return mav;
	}
	
	@GetMapping(value = "/profile")
	public ModelAndView profile() {
		ModelAndView mav;
		Usuario user = userService.loadUserSession();
		
		if(user.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_STUDENT"))) {
			Student student = studentService.findByIdUser();
			mav = new ModelAndView("principal/student/profileStudent");
			mav.addObject("student", student);
		} else {
			Teacher teacher = teacherService.findByIdUser();
			mav = new ModelAndView("principal/teacher/profileTeacher");
			mav.addObject("teacher", teacher);
		}
		
		mav.addObject("user", user);
		
		return mav;
	}
}
