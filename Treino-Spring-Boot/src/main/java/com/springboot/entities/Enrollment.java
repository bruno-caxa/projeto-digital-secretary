package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.springboot.enums.Status;

@Entity
@Table(name ="`enrollment`")
public class Enrollment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Enrollment_pk id = new Enrollment_pk();
	private Status status;
	
	public Enrollment() {
		
	}
	
	public Enrollment(Student student, Course course, Status status) {
		super();
		id.setStudent(student);
		id.setCourse(course);
		this.status = status;
	}

	public Student getStudent() {
		return id.getStudent();
	}
	
	public void setStudent(Student student) {
		id.setStudent(student);
	}
	
	public Course getCourse() {
		return id.getCourse();
	}
	
	public void setCourse(Course course) {
		id.setCourse(course);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
