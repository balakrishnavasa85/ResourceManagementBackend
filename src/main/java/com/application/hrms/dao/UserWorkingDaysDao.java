package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.UserWorkingDays;

@Repository
public interface UserWorkingDaysDao extends JpaRepository<UserWorkingDays, Integer> { 
	
	@Query("select u from UserWorkingDays u where u.user.id =:id and u.year =:year and u.month=:monthname")
    List<UserWorkingDays> getPayslip(@Param("id") Integer id, @Param("monthname") String monthname, @Param("year") Integer year);

	@Query("select u from UserWorkingDays u where u.user.id =:id")
    List<UserWorkingDays> getPayslipById(@Param("id") Integer id);

	@Query("select u from UserWorkingDays u where u.year =:year and u.month=:monthname")
    List<UserWorkingDays> getAllUserSalaryInfo(@Param("monthname") String monthname, @Param("year") Integer year);
	
	@Query("select u from UserWorkingDays u where u.user.id =:user and u.month=:month")
	Optional<UserWorkingDays> findByMonthandUserId(@Param("user") Integer user,@Param("month") String month);	
}
