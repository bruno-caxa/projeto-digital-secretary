package com.springboot.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="`discipline`")
public class Discipline implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer total_classes;
	
	@ManyToOne
	private Teacher teacher;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.discipline")
	private Set<Enrollment> enrollments = new HashSet<>();
	
	public Discipline() {
		
	}
	
	public Discipline(Long id, String name, Teacher teacher) {
		super();
		this.id = id;
		this.name = name;
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getTotal_classes() {
		return total_classes;
	}

	public void setTotal_classes(Integer total_classes) {
		this.total_classes = total_classes;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}
}
