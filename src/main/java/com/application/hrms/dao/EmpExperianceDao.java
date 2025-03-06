package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.EmpExperiance;

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
public interface EmpExperianceDao extends JpaRepository<EmpExperiance,Integer>{
	
	@Query("select u from EmpExperiance u  where u.id =:id")
	List<EmpExperiance> getEmpExperianceById(@Param("id") Integer id);
    
    
    @Query(value="select * from EmpExperiance u where u.companyname=:name", nativeQuery=true)
    EmpExperiance findByName(String name);
    
    @Query(value="select * from EmpExperiance u  where u.id =:id", nativeQuery=true)
    EmpExperiance getEmpExperianceInfoById(@Param("id") Integer id);
    
    @Query("select u from EmpExperiance u  where u.user =:id")
   	List<EmpExperiance> getEmpExperianceByUserId(@Param("id") Integer id);
}
