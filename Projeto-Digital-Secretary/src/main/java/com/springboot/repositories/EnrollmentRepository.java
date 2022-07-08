package com.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.Enrollment;

@Repository
@Transactional
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

	@Query("select e from Enrollment e where e.id.student.id = ?1")
	public List<Enrollment> findByStudent(Long id_student);
	
	@Query("select e from Enrollment e where e.id.discipline.id = ?1")
	public List<Enrollment> findByDiscipline(Long id_discipline);
	
	@Modifying
	@Query("delete from Enrollment e where e.id.student.id = ?1 and e.id.discipline.id = ?2")
	public void unenrollmentStudent(Long id_student, Long id_discipline);
	
}
