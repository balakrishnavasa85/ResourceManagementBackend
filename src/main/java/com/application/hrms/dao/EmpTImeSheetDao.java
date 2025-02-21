package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.EmpTimeSheet;
import com.application.hrms.POJO.Relation;

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
public interface EmpTImeSheetDao extends JpaRepository<EmpTimeSheet, Integer> {

    @Query(value = "select * from EmpTimeSheet u where u.user =:id and u.status =:status order by asc", nativeQuery = true)
    List<EmpTimeSheet> findByEmpIdStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Query(value = "select * from EmpTimeSheet u where u.user =:id and u.logouttime =:checkout", nativeQuery = true)
    EmpTimeSheet findByLogedItem(@Param("id") Integer id, @Param("checkout") String checkout);

    @Query(value = "SELECT " +
            "user, " +
            "STR_TO_DATE(logintime, '%d-%m-%Y') AS workingdate, " +
            "CONCAT(" +
            "LEAST(FLOOR(SUM(workinghours) / 3600000), 9), 'h ', " +
            "CASE " +
            "WHEN SUM(workinghours) / 3600000 >= 9 THEN 0 " +
            "ELSE FLOOR((SUM(workinghours) % 3600000) / 60000) " +
            "END, 'm' " +
            ") AS working_hours, " +
            "CONCAT(" +
            "FLOOR(SUM(workinghours) / 3600000), 'h ', " +
            "FLOOR((SUM(workinghours) % 3600000) / 60000), 'm' " +
            ") AS totalworkinghours, " +
            "CASE " +
            "WHEN SUM(workinghours) > 18000000 THEN 'fullday' " +
            "ELSE 'halfday' " +
            "END AS mode " +
            "FROM EmpTimeSheet " +
            "WHERE logouttime != 'checkout' " +
            "GROUP BY user, STR_TO_DATE(logintime, '%d-%m-%Y') " +
            "ORDER BY user, workingdate", nativeQuery = true)
    List<Object[]> findUserWorkingHours();
}