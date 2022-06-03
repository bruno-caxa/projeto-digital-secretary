package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.Enrollment;

@Repository
@Transactional
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

	@Query("delete from Enrollment e where e.id.student.id = ?1 and e.id.discipline.id = ?2")
	public boolean unenrollmentStudent(Long id_student, Long id_discipline);
}
