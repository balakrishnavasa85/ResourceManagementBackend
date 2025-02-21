package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.Department;
import com.application.hrms.wrapper.DepartmentWrapper;


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
public interface DepartmentDao extends JpaRepository<Department,Integer>{
	@Query("select new com.application.hrms.wrapper.DepartmentWrapper(u.id , u.name , u.status) from Department u")
	List<DepartmentWrapper> getAllDepartments();
	
	@Transactional
    @Modifying
    @Query(value="update Department u set u.status=:status where u.id =:id", nativeQuery=true)
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
	

    @Query("select new com.application.hrms.wrapper.DepartmentWrapper(u.id , u.name , u.status) from Department u  where u.id =:id")
	List<DepartmentWrapper> getDepartmentById(@Param("id") Integer id);
    
    
    @Query(value="select * from Department u where u.name=:name", nativeQuery=true)
    Department findByName(String name);
    
    @Query(value="select * from Department u  where u.id =:id", nativeQuery=true)
    Department getDepartmentInfoById(@Param("id") Integer id);
}
