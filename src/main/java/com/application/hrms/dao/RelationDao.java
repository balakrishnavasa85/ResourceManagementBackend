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
public interface RelationDao extends JpaRepository<Relation, Integer> {

	@Query("select u from Relation u where u.id =:id")
	List<Relation> getRelationById(@Param("id") Integer id);

	@Query("select u from Relation u  where u.user.id =:id")
	List<Relation> getRelationByUserId(@Param("id") Integer id);

	@Query("select u from Relation u where u.name=:name")
	Optional<Relation> findByName(String name);
}
