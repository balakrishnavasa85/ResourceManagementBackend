package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.EmpTimeSheet;
import com.application.hrms.POJO.FormSixteen;
import com.application.hrms.POJO.Relation;
import com.application.hrms.POJO.User;

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
public interface FromSixteenDao extends JpaRepository<FormSixteen, Integer> {	
	
	
	@Query("select u  from FormSixteen u  where u.user.id =:userid")
	List<FormSixteen> findbyuserid(@Param("userid") Integer userid);
}