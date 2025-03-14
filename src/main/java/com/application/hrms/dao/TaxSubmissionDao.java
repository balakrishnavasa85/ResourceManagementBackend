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
import com.application.hrms.POJO.TaxSubmission;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaxSubmissionDao extends JpaRepository<TaxSubmission, Integer> {
	
	@Query("select u from TaxSubmission u where u.user.id=:user")
	Optional<TaxSubmission> findByUser(@Param("user") Integer user);
	 
    @Modifying
    @Transactional
    @Query("DELETE FROM TaxSubmission u WHERE u.user.id = :userid")
    void deleteByUser(@Param("userid") Integer userid);
}
