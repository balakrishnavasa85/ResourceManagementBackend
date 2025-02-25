package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
public interface DeductionGroupDao extends JpaRepository<DeductionGroup, Integer> {
	@Query("select new com.application.hrms.wrapper.DeductionGroupWrapper(u.id , u.name ,u.value, u.status) from DeductionGroup u")
	List<DeductionGroupWrapper> getAll();

	@Transactional
	@Modifying
	@Query(value = "update DeductionGroup u set u.status=:status where u.id =:id", nativeQuery = true)
	Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

	@Query("select new com.application.hrms.wrapper.DeductionGroupWrapper(u.id , u.name ,u.value, u.status) from DeductionGroup u  where u.id =:id")
	List<DeductionGroupWrapper> getDeductionGroupById(@Param("id") Integer id);

	@Query("select u from DeductionGroup u where u.name=:name")
	DeductionGroup findByName(String name);

	@Query("select u from DeductionGroup u  where u.id =:id")
	DeductionGroup getDeductionGroupInfoById(@Param("id") Integer id);
}
