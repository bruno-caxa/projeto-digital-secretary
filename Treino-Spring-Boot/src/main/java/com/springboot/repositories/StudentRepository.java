package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.Student;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query("select s from Student s where s.usuario.id = ?1")
	public Student getByIdUser(Long id);
}
