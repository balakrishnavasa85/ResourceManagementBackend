package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.Schdules;

@Repository
public interface SchdulesDao extends JpaRepository<Schdules, Integer> {
	

	@Query("select u from Schdules u where u.status ='schduled' and u.interviewerid =:id")
	List<Schdules> checkUserInterviews(@Param("id") Integer id);

	@Query("select u from Schdules u where u.status ='schduled' and u.interviewerid =:id")
	Schdules checkUserInterview(@Param("id") Integer id);

}
