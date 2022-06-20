package com.springboot.services;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Discipline;
import com.springboot.entities.Enrollment;
import com.springboot.entities.Student;
import com.springboot.repositories.DisciplineRepository;
import com.springboot.repositories.EnrollmentRepository;

@Service
public class DisciplineService {
	
	@Autowired
	private DisciplineRepository disciplineRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private StudentService studentService;
	
	public List<Discipline> findAllDisciplines() {
		return disciplineRepository.findAll();
	}
	
	public List<Discipline> findDisciplinesIsNotEnrolled() {
		List<Discipline> disciplines = disciplineRepository.findAll();
		Student student = studentService.findByIdUser();
		List<Enrollment> enrollmentsStudent = enrollmentRepository.findByStudent(student.getId());
		
		//Apresentar somente as disciplinas que o estudante não está matriculado
		for(Iterator<Discipline> i = disciplines.iterator(); i.hasNext();) {
			Discipline d = i.next();
			for(Iterator<Enrollment> j = enrollmentsStudent.iterator(); j.hasNext();) {
				Enrollment e = j.next();
				
				if(d.getName().equals(e.getDiscipline().getName())) {
					i.remove();
					break;
				}
			}
		}
		return disciplines;
	}
	
	public List<Discipline> findDisciplinesWithoutTeacher() {
		return disciplineRepository.findAll()
								   .stream()
								   .filter(d -> d.getTeacher() == null)
								   .collect(Collectors.toList());
	}
	
}
