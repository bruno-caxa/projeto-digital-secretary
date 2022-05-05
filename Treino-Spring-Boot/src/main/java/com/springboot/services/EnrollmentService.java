package com.springboot.services;

import java.util.List;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Course;
import com.springboot.entities.Enrollment;
import com.springboot.entities.Student;
import com.springboot.enums.Status;
import com.springboot.repositories.CourseRepository;
import com.springboot.repositories.EnrollmentRepository;
import com.springboot.repositories.StudentRepository;
import com.springboot.services.exceptions.DatabaseException;

@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public Enrollment enrollmentStudent(Long id_student, Long id_course) {
		try {
			Student student = studentRepository.findById(id_student).get();
			Course course = courseRepository.findById(id_course).get();
			
			return enrollmentRepository.save(new Enrollment(student, course, Status.STUDYING));
		} catch(PropertyValueException e) {
			throw new DatabaseException("Student data is not complete!");
		}
	}
	
	public List<Enrollment> findAll() {
		return enrollmentRepository.findAll();
	}
}
