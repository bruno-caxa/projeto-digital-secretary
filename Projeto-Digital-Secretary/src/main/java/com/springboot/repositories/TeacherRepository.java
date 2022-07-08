package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.Teacher;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	@Query("select t from Teacher t where t.usuario.id = ?1")
	public Teacher getByIdUser(Long id);
}
