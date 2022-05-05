package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Enrollment_pk implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne()
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Enrollment_pk() {
		
	}
	
	public Enrollment_pk(Course course, Student student) {
		super();
		this.course = course;
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
