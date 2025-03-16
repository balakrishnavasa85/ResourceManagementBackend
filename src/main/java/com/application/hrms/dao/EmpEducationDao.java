package com.application.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.EmpEducation;

@Repository
public interface EmpEducationDao extends JpaRepository<EmpEducation, Integer> {

	@Query("select u from EmpEducation u  where u.id =:id")
	List<EmpEducation> getEmpEducationById(@Param("id") Integer id);

	@Query(value = "select * from EmpEducation u where u.study=:name", nativeQuery = true)
	EmpEducation findByName(String name);

	@Query(value = "select * from EmpEducation u  where u.id =:id", nativeQuery = true)
	EmpEducation getEmpEducationInfoById(@Param("id") Integer id);

	@Query("select u from EmpEducation u  where u.user =:id")
	List<EmpEducation> getEmpEducationByUserId(@Param("id") Integer id);
}
