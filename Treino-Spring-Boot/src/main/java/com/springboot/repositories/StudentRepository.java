package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
