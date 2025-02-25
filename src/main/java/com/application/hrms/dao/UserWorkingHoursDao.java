package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.UserWorkingHours;

import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkingHoursDao extends JpaRepository<UserWorkingHours, Integer> {

    @Query("select u from UserWorkingHours u where u.user.id = :id")
    List<UserWorkingHours> findByUser(@Param("id") Integer id);

    @Query("select u from UserWorkingHours u where u.user.id = :id and FUNCTION('MONTH', u.workingdate) = :month and FUNCTION('YEAR', u.workingdate) = :year")
    List<UserWorkingHours> findByUserAndMonth(@Param("id") Integer id, @Param("month") Integer month, @Param("year") Integer year);

    @Query(value = "SELECT count(*) as dayscount, u.user_id, DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%M') as month, " +
                   "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y') AS year, " +
                   "DAY(LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS lastmonthnumberofdays, " +
                   "(SELECT count(*) FROM hrmsnew.holidays WHERE date BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-25') " +
                   "AND DATE_FORMAT(CURDATE(), '%Y-%m-24') AND DAYOFWEEK(date) NOT IN (1, 7)) AS numberofholidays , us.name as username " +
                   ",sd.accountnumber, sd.ifsccode, sd.pf, sd.uan, sd.bankname " +
                   "FROM hrmsnew.userworkinghours u " +
                   "JOIN hrmsnew.user us ON u.user_id = us.id " +
                   "JOIN hrmsnew.salarydetails sd ON u.user_id = sd.user_id " +
                   "WHERE u.workingdate BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-25') " +
                   "AND DATE_FORMAT(CURDATE(), '%Y-%m-24') " +
                   "GROUP BY u.user_id, us.name, sd.id", nativeQuery = true)
    List<Object[]> generatePayslip();
}