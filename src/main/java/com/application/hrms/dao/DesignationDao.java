package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.wrapper.DepartmentWrapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.wrapper.DesignationWrapper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DesignationDao extends JpaRepository<Designation,Integer>{
	@Query("select new com.application.hrms.wrapper.DesignationWrapper(u.id , u.name , u.status) from Designation u")
	List<DesignationWrapper> getAll();
	
	@Transactional
    @Modifying
    @Query(value="update Designation u set u.status=:status where u.id =:id", nativeQuery=true)
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
	

    @Query("select new com.application.hrms.wrapper.DesignationWrapper(u.id , u.name , u.status) from Designation u  where u.id =:id")
	List<DesignationWrapper> getDesignationById(@Param("id") Integer id);
    
    
    @Query(value="select * from Designation u where u.name=:name", nativeQuery=true)
    Designation findByName(String name);
    
    @Query(value="select * from Designation u  where u.id =:id", nativeQuery=true)
    Designation getDesignationInfoById(@Param("id") Integer id);
}
