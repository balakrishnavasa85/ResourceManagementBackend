package com.application.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.FormSixteen;

@Repository
public interface FromSixteenDao extends JpaRepository<FormSixteen, Integer> {	
	
	
	@Query("select u  from FormSixteen u  where u.user.id =:userid")
	List<FormSixteen> findbyuserid(@Param("userid") Integer userid);
}