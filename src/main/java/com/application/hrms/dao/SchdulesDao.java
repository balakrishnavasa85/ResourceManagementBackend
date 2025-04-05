package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.application.hrms.wrapper.RecruitmentDetailsDTO;

import com.application.hrms.POJO.Schdules;

@Repository
public interface SchdulesDao extends JpaRepository<Schdules, Integer> {
	

	@Query("select u from Schdules u where u.status ='schduled' and u.interviewerid =:id")
	List<Schdules> checkUserInterviews(@Param("id") Integer id);
	
	@Query("select u from Schdules u where u.userprocess.id =:id and u.status = 'next'")
	List<Schdules> checkPreviousHistory(@Param("id") Integer id);
	
	@Query("select u from Schdules u where u.status ='schduled' and u.interviewerid =:id")
	Schdules checkUserInterview(@Param("id") Integer id);
	
    @Query("SELECT DISTINCT new com.application.hrms.wrapper.RecruitmentDetailsDTO(" +
            "s.id, s.interviewerid, s.interviewtime, s.status, s.interviewername, " +
    		"s.takentime, s.comment, "+
            "up.id, up.name, up.email, up.contact, up.aadhar, up.pan, " +
            "up.expectedsalary, up.experianceinmonths, up.dob, up.joiningon, up.filepath, " +
            "r.id, r.reqid, r.title, r.description, r.status, r.noofpositions, " +
            "r.noofpositionsclosed, r.budget, d.id, d.name,de.id, de.name, ra.id,ra.user.id " +
            ",up.opuserid  ,up.opusername  ,up.opusercomment  ,up.preferedjoingdate  ,up.userconformation  "+
            ",up.operationdate, up.userlink,up.offergenerateddate,up.address,up.gender,up.maritalstatus,up.onboard  " +
             ") " +
            "FROM Schdules s " +
            "JOIN s.userprocess up " +
            "JOIN up.recrutmentassigners ra " +
            "JOIN ra.recrutment r " +
            "JOIN r.department d " +
            "JOIN r.designation de " +
            "WHERE s.interviewerid = :interviewerId and s.status = 'schduled'" +
            "ORDER BY s.interviewtime")
     List<RecruitmentDetailsDTO> findSchedulesByInterviewerId(@Param("interviewerId") Integer interviewerId);
    
    @Query("SELECT DISTINCT new com.application.hrms.wrapper.RecruitmentDetailsDTO(" +
            "s.id, s.interviewerid, s.interviewtime, s.status, s.interviewername, " +
    		"s.takentime, s.comment, "+
            "up.id, up.name, up.email, up.contact, up.aadhar, up.pan, " +
            "up.expectedsalary, up.experianceinmonths, up.dob, up.joiningon, up.filepath, " +
            "r.id, r.reqid, r.title, r.description, r.status, r.noofpositions, " +
            "r.noofpositionsclosed, r.budget, d.id, d.name,de.id, de.name, ra.id,ra.user.id " +
            ",up.opuserid  ,up.opusername  ,up.opusercomment  ,up.preferedjoingdate  ,up.userconformation  "
            + ",up.operationdate, up.userlink,up.offergenerateddate,up.address,up.gender,up.maritalstatus,up.onboard  " +
             ") " +
            "FROM Schdules s " +
            "JOIN s.userprocess up " +
            "JOIN up.recrutmentassigners ra " +
            "JOIN ra.recrutment r " +
            "JOIN r.department d " +
            "JOIN r.designation de " +
            "WHERE s.status ='selected'" +
            "ORDER BY s.interviewtime")
     List<RecruitmentDetailsDTO> slectedUser();
    
    @Transactional
	@Modifying
	@Query(value = "update Schdules u set u.comment=:comment, u.takentime =:takentime, u.status =:status where u.id =:schduleid", nativeQuery = true)
	Integer updateFeedback(@Param("comment") String comment, @Param("schduleid") Integer schduleid,@Param("takentime") String takentime,@Param("status") String status);

}
