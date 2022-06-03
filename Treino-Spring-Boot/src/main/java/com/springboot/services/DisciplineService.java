package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Discipline;
import com.springboot.repositories.DisciplineRepository;

@Service
public class DisciplineService {

	@Autowired
	private DisciplineRepository disciplineRepository;
	
	public List<Discipline> loadAllDisciplines() {
		return disciplineRepository.findAll();
	}

}
