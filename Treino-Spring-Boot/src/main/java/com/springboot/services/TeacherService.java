package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Teacher;
import com.springboot.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	public Teacher create(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public void delete(Long id) {
		teacherRepository.deleteById(id);
	}
	
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	public Teacher findById(Long id) {
		return teacherRepository.findById(id).get();
	}
	
}
