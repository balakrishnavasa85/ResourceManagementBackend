package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.UserWorkingDays;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserWorkingDaysDao extends JpaRepository<UserWorkingDays, Integer> { 
	
	@Query("select u from UserWorkingDays u where u.user.id =:id and u.year =:year and u.month=:monthname")
    List<UserWorkingDays> getPayslip(@Param("id") Integer id, @Param("monthname") String monthname, @Param("year") Integer year);

	@Query("select u from UserWorkingDays u where u.user.id =:id")
    List<UserWorkingDays> getPayslipById(@Param("id") Integer id);

}
