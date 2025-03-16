package com.application.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.Holidays;

@Repository
public interface HolidaysDao extends JpaRepository<Holidays, Integer> {

	 @Query(value = "SELECT COUNT(*) FROM holidays " +
                   "WHERE holidaydate BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-25') " +
                   "AND DATE_FORMAT(CURDATE(), '%Y-%m-24') " +
                   "AND DAYOFWEEK(holidaydate) NOT IN (1, 7)", nativeQuery = true)
    Integer countHolidaysExcludingWeekends();

}
