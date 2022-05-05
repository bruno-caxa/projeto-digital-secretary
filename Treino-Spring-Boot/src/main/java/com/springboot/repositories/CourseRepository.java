package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
