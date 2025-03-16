package com.application.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.ApplicationAccess;

@Repository
public interface ApplicationAccessDao extends JpaRepository<ApplicationAccess, Integer> {
	 @Query("SELECT u FROM ApplicationAccess u WHERE  CURRENT_DATE BETWEEN u.fromaccess AND u.uptoaccess and u.name =:name")
	    List<ApplicationAccess> findAccessValidToday(@Param("name") String name);
	 
	 
	 @Query("SELECT u FROM ApplicationAccess u WHERE u.name != 'HRMS'")
	    List<ApplicationAccess> findExceptMain();
}
