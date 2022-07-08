package com.springboot.services;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.entities.Enrollment;
import com.springboot.entities.Role;
import com.springboot.entities.Student;
import com.springboot.entities.Usuario;
import com.springboot.repositories.RoleRepository;
import com.springboot.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public void delete(Long idStudent) {
		studentRepository.deleteById(idStudent);
	}
	
	public Student save(Student student, Usuario user) {
		if(user.getUsername() != null) {
			Role role = roleRepository.getById(2l);
			String passwordEncode = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(passwordEncode);
			user.getRoles().add(role);
		} else {
			user = userService.loadUserSession();
		}
		
		student.setUsuario(user);
		return studentRepository.save(student);
	}
	
	public List<Student> findAllStudents() {
		List<Student> students = studentRepository.findAll();
		students.sort(Comparator.comparing(Student::getName));
		return students;
	}
	
	public Student findById(Long id) {
		return studentRepository.findById(id).get();
	}
	
	public Student findByIdUser() {
		Usuario usuario = userService.loadUserSession();
		return studentRepository.getByIdUser(usuario.getId());
	}
	
	public Set<Enrollment> findEnrollments() {
		Usuario usuario = userService.loadUserSession();
		return studentRepository.getByIdUser(usuario.getId()).getEnrollments();
	}
	
}
