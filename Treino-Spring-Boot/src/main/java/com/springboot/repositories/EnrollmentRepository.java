package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

}
