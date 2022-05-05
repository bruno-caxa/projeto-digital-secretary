package com.springboot.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="`student`")
public class Student extends User {
	
	private static final long serialVersionUID = 1L;

	@Column(length = 20, nullable = false)
	private String ra;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.student")
	private Set<Enrollment> enrollments = new HashSet<>();
	
	public Student() {
		
	}
	
	public Student(Long id, String login, String password, String ra, 
				   String name, String telephone, String email) {
		super(id, login, password, name, telephone, email);
		this.ra = ra;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}
	
	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

}
