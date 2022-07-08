package com.springboot.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Discipline;
import com.springboot.entities.Enrollment;
import com.springboot.entities.Student;
import com.springboot.enums.Status;
import com.springboot.repositories.DisciplineRepository;
import com.springboot.repositories.EnrollmentRepository;
import com.springboot.repositories.StudentRepository;

@Service
public class EnrollmentService {

	@Autowired
	private DisciplineRepository disciplineRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Enrollment enrollmentStudent(Long id_student, Long id_discipline) {
		Student student = studentRepository.findById(id_student).get();
		Discipline discipline = disciplineRepository.findById(id_discipline).get();
		Date dateStart = Calendar.getInstance().getTime();
		Date dateEnd = addDays(dateStart, discipline.getTotal_classes());
		Enrollment enrollment = new Enrollment(student, discipline, Status.STUDYING, dateStart, dateEnd);
		
		return enrollmentRepository.save(enrollment);
	}
	
	public void unenrollmentStudent(Long id_student, Long id_discipline) {
		enrollmentRepository.unenrollmentStudent(id_student, id_discipline);
	}
	
	private Date addDays(Date date, Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}
}
