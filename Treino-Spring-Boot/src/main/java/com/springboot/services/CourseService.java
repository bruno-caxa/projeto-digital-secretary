package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Course;
import com.springboot.repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Course create(Course course) {
		return courseRepository.save(course);
	}
	
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	public Course findById(Long id) {
		return courseRepository.findById(id).get();
	}
}
