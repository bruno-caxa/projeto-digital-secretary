package com.springboot.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
	
	@Column(nullable = false)
	private Status status;
	
	@Column(nullable = false)
	private Date dateStart;
	
	@Column(nullable = false)
	private Date dateEnd;
	
	public Enrollment() {
		
	}
	
	public Enrollment(Student student, Discipline discipline, Status status, Date dateStart, Date dateEnd) {
		super();
		id.setStudent(student);
		id.setDiscipline(discipline);
		this.status = status;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public Student getStudent() {
		return id.getStudent();
	}
	
	public void setStudent(Student student) {
		id.setStudent(student);
	}
	
	public Discipline getDiscipline() {
		return id.getDiscipline();
	}
	
	public void setDiscipline(Discipline discipline) {
		id.setDiscipline(discipline);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
}
