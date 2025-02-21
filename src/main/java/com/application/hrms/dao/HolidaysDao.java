package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.Holidays;

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
public interface HolidaysDao extends JpaRepository<Holidays, Integer> {

	 @Query(value = "SELECT COUNT(*) FROM hrms.holidays " +
                   "WHERE holidaydate BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-25') " +
                   "AND DATE_FORMAT(CURDATE(), '%Y-%m-24') " +
                   "AND DAYOFWEEK(holidaydate) NOT IN (1, 7)", nativeQuery = true)
    Integer countHolidaysExcludingWeekends();

}
