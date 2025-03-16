package com.application.hrms.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.Leaves;

@Repository
public interface LeavesDao extends JpaRepository<Leaves, Integer> {

	@Transactional
	@Modifying
	@Query(value = "update leaves u set u.balance=:balance where u.id =:id", nativeQuery = true)
	Integer updateLeaves(@Param("balance") Integer balace,@Param("id") Integer id); 
	  
	@Query("select u  from Leaves u  where u.user.id =:id")
	Leaves getLeavesByUserId(@Param("id") Integer id);
	 
	 
}
