package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Student;
import com.springboot.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student create(Student student) {
		return studentRepository.save(student);
	}
	
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
	
	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	public Student findById(Long id) {
		return studentRepository.findById(id).get();
	}
	
}
