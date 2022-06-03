package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.Discipline;

@Repository
@Transactional
public interface DisciplineRepository extends JpaRepository<Discipline, Long>{

}
