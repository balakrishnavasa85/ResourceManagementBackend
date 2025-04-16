package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.ApplicationAccess;
import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.POJO.AppraisalCriteria;
import com.application.hrms.POJO.AppraisalCycles;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.wrapper.DeductionGroupWrapper;

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
public interface AppraisalCriteriaDao extends JpaRepository<AppraisalCriteria, Integer> { 
	
	@Query("select u from AppraisalCriteria u where u.name=:name")
	AppraisalCriteria findByName(String name);

	@Transactional
    @Modifying
    @Query(value="update AppraisalCriteria u set u.status=:status where u.id =:id", nativeQuery=true)
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
	
	@Query("select u from AppraisalCriteria u where u.status = 'y'")
	List<AppraisalCriteria> getAllActive();
	
	@Query("select u from AppraisalCriteria u  where u.id =:id")
	List<AppraisalCriteria> getValuesById(@Param("id") Integer id);
	
	@Query("select u from AppraisalCriteria u  where u.category.id =:id")
	List<AppraisalCriteria> loadCriterias(@Param("id") Integer id);
	
}
