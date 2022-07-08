package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query("select r from Role r where r.nomeRole = ?1")
	public Role getRoleForName(String nomeRole);
}
