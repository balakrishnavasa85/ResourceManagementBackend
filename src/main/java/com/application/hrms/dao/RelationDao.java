package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Relation;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RelationWrapper;

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
public interface RelationDao extends JpaRepository<Relation,Integer>{
	@Query("select new com.application.hrms.wrapper.RelationWrapper(u.id , u.name , u.status,u.relation,u.user) from Relation u")
	List<RelationWrapper> getAllRelations();
	
	@Transactional
    @Modifying
    @Query(value="update Relation u set u.status=:status where u.id =:id", nativeQuery=true)
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
	

    @Query("select new com.application.hrms.wrapper.RelationWrapper(u.id , u.name , u.status,u.relation,u.user) from Relation u  where u.id =:id")
	List<RelationWrapper> getRelationById(@Param("id") Integer id);
    
    
    @Query(value="select * from Relation u where u.name=:name", nativeQuery=true)
    Relation findByName(String name);
    
    @Query(value="select * from Relation u  where u.id =:id", nativeQuery=true)
    Relation getRelationInfoById(@Param("id") Integer id);
    
    @Query("select new com.application.hrms.wrapper.RelationWrapper(u.id , u.name , u.status,u.relation,u.user) from Relation u  where u.user =:id")
   	List<RelationWrapper> getRelationByUserId(@Param("id") Integer id);
}
