package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.ApplicationAccess;
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
public interface ApplicationAccessDao extends JpaRepository<ApplicationAccess, Integer> {
	 @Query(value = "SELECT * FROM ApplicationAccess WHERE uptoaccess > CURRENT_DATE", nativeQuery = true)
	    List<ApplicationAccess> findAccessValidToday();
}
