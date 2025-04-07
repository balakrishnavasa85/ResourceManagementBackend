package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.Tds;
@Repository
public interface RecrutmentDao extends JpaRepository<Recrutment,Integer>{
	
	@Query("Select u from Recrutment u where u.title =:title")
	Optional<Recrutment> findByTitle(@Param("title") String title);
	
	@Query("Select u from Recrutment u where u.id IN (SELECT ra.recrutment.id FROM RecrutmentAssigners ra WHERE ra.id = :id)")
	List<Recrutment> getByIdAssigner(@Param("id") Integer id);
	
	@Query("Select u from Recrutment u where u.status ='y'")
	List<Recrutment> getAllActive();
	
	@Query("Select u from Recrutment u where u.id =:reqid")
	Recrutment findByReqId(@Param("reqid") Integer reqid);
	
	@Query("SELECT r FROM Recrutment r WHERE r.id IN (SELECT ra.recrutment.id FROM RecrutmentAssigners ra WHERE ra.user.id = :userid)")
		List<Recrutment> findRecruitmentsByUserId(@Param("userid") Integer userid);
	
	@Transactional
	@Modifying
	@Query("update Recrutment u set u.noofpositionsclosed =:noofopositionsCount where u.id =:recrutmentId")
	Integer updatePositions(@Param("noofopositionsCount") Integer noofopositionsCount,@Param("recrutmentId") Integer recrutmentId);	
}
