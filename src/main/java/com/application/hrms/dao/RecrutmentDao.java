package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.Tds;
@Repository
public interface RecrutmentDao extends JpaRepository<Recrutment,Integer>{
	
	@Query("Select u from Recrutment u where u.title =:title")
	Optional<Recrutment> findByTitle(@Param("title") String title);
	
	@Query("Select u from Recrutment u where u.status ='y'")
	List<Recrutment> getAllActive();
	
}
