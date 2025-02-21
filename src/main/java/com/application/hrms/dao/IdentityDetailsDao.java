package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.IdentityDetails;
import com.application.hrms.POJO.SalaryDetails;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityDetailsDao extends JpaRepository<IdentityDetails, Integer> {
	
	@Query("select u from IdentityDetails u where u.user=:user")
	Optional<IdentityDetails> findByUser(Integer user);
	 
}
