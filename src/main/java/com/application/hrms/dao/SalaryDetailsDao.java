package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.SalaryDetails;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryDetailsDao extends JpaRepository<SalaryDetails, Integer> {
	
	@Query("select u from SalaryDetails u where u.user=:user")
	Optional<SalaryDetails> findByUser(Integer user);
	 
}
