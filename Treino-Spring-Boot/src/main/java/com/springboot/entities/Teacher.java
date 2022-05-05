package com.springboot.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="`teacher`")
public class Teacher extends User {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private Set<Course> courses = new HashSet<>();
	
	public Teacher() {
		
	}
	
	public Teacher(Long id, String login, String password, String name, String telephone, String email) {
		super(id, login, password, name, telephone, email);
	}

	public Set<Course> getCourses() {
		return courses;
	}

}
