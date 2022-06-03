package com.springboot.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="`teacher`")
public class Teacher extends People {

	private static final long serialVersionUID = 1L;

	
	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private Set<Discipline> disciplines = new HashSet<>();
	
	public Teacher() {
		
	}
	
	public Teacher(Long id, String name, String telephone, String email) {
		super(id, name, telephone, email);
	}
	
	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

}
