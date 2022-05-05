package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 15, nullable = false)
	private String login;
	
	@Column(length = 15, nullable = false)
	private String password;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 100)
	private String telephone;
	
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	public User() {
		
	}
	
	public User(Long id, String login, String password, String name, String telephone, String email) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
