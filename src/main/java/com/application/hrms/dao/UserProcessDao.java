package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.UserProcess;
import com.application.hrms.wrapper.UserProcessWrapper;
@Repository
public interface UserProcessDao extends JpaRepository<UserProcess,Integer>{

	@Query("Select u from UserProcess u where u.aadhar =:aadhar and u.pan =:pan")
	List<UserProcess> checkValue(@Param("aadhar") String aadhar,@Param("pan") String pan);	
	
	@Query("SELECT new com.application.hrms.wrapper.UserProcessWrapper( " +
		       "r.id, r.reqid, r.title, r.description, r.budget, " +
		       "r.noofpositions, r.noofpositionsclosed, d, u, up.id, " + // Fetch Department (d) & User (u) objects
		       "up.name, up.email, up.contact, up.aadhar, up.dob, " +
		       "up.expectedsalary, up.experianceinmonths, up.joiningon, " +
		       "up.pan, up.filepath) " +
		       "FROM UserProcess up " +
		       "JOIN up.recrutment r " +
		       "JOIN r.department d " +    // Fetch Department object
		       "JOIN RecrutmentAssigners ra ON ra.recrutment.id = r.id " +
		       "JOIN User u ON ra.user.id = u.id " + // Fetch User object as assigner
		       "WHERE r.noofpositions > COALESCE(r.noofpositionsclosed, 0) " +
		       "AND up.recruter = ra.user.id")
		List<UserProcessWrapper> getRecruitmentUserProcess();


}
