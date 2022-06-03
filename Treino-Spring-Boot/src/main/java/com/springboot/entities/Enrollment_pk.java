package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Enrollment_pk implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	@JoinColumn(name = "discipline_id")
	private Discipline discipline;
	
	@ManyToOne()
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Enrollment_pk() {
		
	}
	
	public Enrollment_pk(Discipline discipline, Student student) {
		super();
		this.discipline = discipline;
		this.student = student;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
