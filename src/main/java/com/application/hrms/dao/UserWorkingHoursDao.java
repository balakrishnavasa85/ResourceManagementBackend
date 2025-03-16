package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.UserWorkingHours;

@Repository
public interface UserWorkingHoursDao extends JpaRepository<UserWorkingHours, Integer> {

    @Query("select u from UserWorkingHours u where u.user.id = :id")
    List<UserWorkingHours> findByUser(@Param("id") Integer id);

    @Query("select u from UserWorkingHours u where u.user.id = :id and FUNCTION('MONTH', u.workingdate) = :month and FUNCTION('YEAR', u.workingdate) = :year")
    List<UserWorkingHours> findByUserAndMonth(@Param("id") Integer id, @Param("month") Integer month, @Param("year") Integer year);

    @Query(value = "SELECT count(*) as dayscount, u.user_id, DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%M') as month, " +
                   "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y') AS year, " +
                   "DAY(LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS lastmonthnumberofdays, " +
                   "(SELECT count(*) FROM holidays WHERE date BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-25') " +
                   "AND DATE_FORMAT(CURDATE(), '%Y-%m-24') AND DAYOFWEEK(date) NOT IN (1, 7)) AS numberofholidays , us.name as username " +
                   ",sd.accountnumber, sd.ifsccode, sd.pf, sd.uan, sd.bankname " +
                   "FROM userworkinghours u " +
                   "JOIN user us ON u.user_id = us.id " +
                   "JOIN salarydetails sd ON u.user_id = sd.user_id " +
                   "WHERE u.workingdate BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-25') " +
                   "AND DATE_FORMAT(CURDATE(), '%Y-%m-24') AND us.status = 'y' " +
                   "GROUP BY u.user_id, us.name, sd.id", nativeQuery = true)
    List<Object[]> generatePayslip();
    
    

    @Transactional
    @Modifying
    @Query("update UserWorkingHours u set u.status= '1' where u.user.id =:user")
    Integer updateStatus(@Param("user") Integer user);
}
