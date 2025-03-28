package com.application.hrms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.RecrutmentAssigners;
@Repository
public interface RecrutmentAssignersDao extends JpaRepository<RecrutmentAssigners,Integer>{
	
	@Query("select u from RecrutmentAssigners u where recrutment.id =:reqid ")
	Optional<RecrutmentAssigners> findByReqId(@Param("reqid") Integer reqid);

	
}
