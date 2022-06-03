package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Student;
import com.springboot.entities.Usuario;
import com.springboot.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student save(Student student) {
		student.setUsuario(userService.loadUserSession());
		return studentRepository.save(student);
	}
	
	public Student loadByIdUser() {
		Usuario usuario = userService.loadUserSession();
		return studentRepository.getByIdUser(usuario.getId());
	}
	
	public List<Student> loadAllStudents() {
		return studentRepository.findAll();
	}
}
