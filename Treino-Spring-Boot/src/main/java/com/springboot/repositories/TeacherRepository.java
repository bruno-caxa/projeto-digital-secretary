package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
