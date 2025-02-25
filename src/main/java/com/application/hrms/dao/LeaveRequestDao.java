package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.hrms.POJO.LeaveRequest;
import com.application.hrms.POJO.User;

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
public interface LeaveRequestDao extends JpaRepository<LeaveRequest, Integer> {

//	@Transactional
//	@Modifying
//	@Query(value = "update LeaveRequest u set u.approvedby=:approverid where u.id =:leaverequestid", nativeQuery = true)
//	Integer updateApprove(@Param("leaverequestid") Integer leaverequestid, @Param("approverid") Integer approverid);

	@Transactional
	@Modifying
	@Query(value = "update LeaveRequest u set u.approvedby=:approverid, u.comment =:comment, u.status =:status where u.id =:leaverequestid", nativeQuery = true)
	Integer updateApproveReject(@Param("leaverequestid") Integer leaverequestid, @Param("approverid") Integer approverid,
			@Param("comment") String comment, @Param("status") String status);
	
	@Query("select u  from LeaveRequest u  where u.user.id =:id")
	List<LeaveRequest> 	findApplyedLeaveInfo(@Param("id") Integer id);
	

	@Query("select u  from LeaveRequest u  where u.approvedby =:id and u.status = 'apply'")
	List<LeaveRequest> 	leaverequestneedtoapprove(@Param("id") Integer id);

	@Query(value = "SELECT SUM(noofdays) AS approveddays " +
                   "FROM LeaveRequest " +
                   "WHERE status = 'approved' and user_id =:id " +
                   "AND STR_TO_DATE(requestfromdate, '%d-%m-%Y') >= STR_TO_DATE(CONCAT('25-', MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)), '-', YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))), '%d-%m-%Y') " +
                   "AND STR_TO_DATE(requesttodate, '%d-%m-%Y') <= STR_TO_DATE(CONCAT('24-', MONTH(CURDATE()), '-', YEAR(CURDATE())), '%d-%m-%Y')", nativeQuery = true)
    Integer countApprovedLeaves(@Param("id") Integer id);
	
	@Query(value = "WITH RECURSIVE DateSeries AS ( "
			+ "SELECT STR_TO_DATE(l.requestfromdate, '%d-%m-%Y') AS date_value, "
			+ "STR_TO_DATE(l.requesttodate, '%d-%m-%Y') AS requesttodate "
			+ "FROM hrmsnew.leaverequest l "
			+ "WHERE l.status !='rejected' and l.user_id = :userId "
			+ "UNION ALL "
			+ "SELECT DATE_ADD(date_value, INTERVAL 1 DAY), requesttodate "
			+ "FROM DateSeries "
			+ "WHERE date_value < requesttodate ) "
			+ "SELECT DISTINCT DATE_FORMAT(date_value, '%Y-%m-%d') "
			+ "FROM DateSeries "
			+ "WHERE date_value BETWEEN STR_TO_DATE(:fromDate, '%d-%m-%Y') "
			+ "AND STR_TO_DATE(:toDate, '%d-%m-%Y') AND DAYOFWEEK(date_value) NOT IN (1, 7)", nativeQuery = true)
	    List<String> findLeaveDates(@Param("userId") Integer userId,
	                                @Param("fromDate") String fromDate,
	                                @Param("toDate") String toDate);

}
