package com.springboot.services;

import java.util.ArrayList;
import java.util.Comparator;
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
	
	public void delete(Long id) {
		disciplineRepository.deleteById(id);
	}
	
	public List<Discipline> findAll() {
		List<Discipline> disciplines = disciplineRepository.findAll();
		disciplines.sort(Comparator.comparing(Discipline::getName));
		return disciplines;
	}
	
	public Discipline findById(Long id) {
		return disciplineRepository.findById(id).get();
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
		disciplines.sort(Comparator.comparing(Discipline::getName));
		return disciplines;
	}
	
	public List<Discipline> findDisciplinesWithoutTeacher() {
		List<Discipline> disciplines =  disciplineRepository.findAll()
								   						    .stream()
								   						    .filter(d -> d.getTeacher() == null)
								   						    .collect(Collectors.toList());
		disciplines.sort(Comparator.comparing(Discipline::getName));
		return disciplines;
	}
	
	public void save(Discipline discipline) {
		List<Enrollment> enrollments = enrollmentRepository.findByDiscipline(discipline.getId());
		discipline.setEnrollments(enrollments);
		disciplineRepository.save(discipline);
	}
	
	public List<Student> studentsEnrolled(Long id) {
		List<Student> students = new ArrayList<>();
		Discipline discipline = findById(id);
		discipline.getEnrollments().forEach(e -> students.add(e.getStudent()));
		students.sort(Comparator.comparing(Student::getName));
		return students;
	}

	
}
