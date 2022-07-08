package com.springboot.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.entities.Discipline;
import com.springboot.entities.Role;
import com.springboot.entities.Teacher;
import com.springboot.entities.Usuario;
import com.springboot.repositories.RoleRepository;
import com.springboot.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public void delete(Long id) {
		teacherRepository.deleteById(id);
	}
	
	public List<Teacher> findAllTeachers() {
		return teacherRepository.findAll();
	}
	
	public Teacher findById(Long id) {
		return teacherRepository.findById(id).get();
	}
	
	public Teacher findByIdUser() {
		Usuario usuario = userService.loadUserSession();
		return teacherRepository.getByIdUser(usuario.getId());
	}

	public Set<Discipline> findDisciplines() {
		Usuario usuario = userService.loadUserSession();
		return teacherRepository.getByIdUser(usuario.getId()).getDisciplines();
	}
	
	public Teacher save(Teacher teacher, Usuario user) {
		if(user.getUsername() != null) {
			Role role = roleRepository.getById(3l);
			String passwordEncode = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(passwordEncode);
			user.getRoles().add(role);
		} else {
			user = userService.loadUserSession();
		}
		teacher.setUsuario(user);
		return teacherRepository.save(teacher);
	}
}
