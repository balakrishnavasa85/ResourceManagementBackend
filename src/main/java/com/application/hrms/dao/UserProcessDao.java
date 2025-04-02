package com.application.hrms.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.UserProcess;
import com.application.hrms.wrapper.UserProcessWrapper;
@Repository
public interface UserProcessDao extends JpaRepository<UserProcess,Integer>{

	@Query("Select u from UserProcess u where u.aadhar =:aadhar and u.pan =:pan")
	List<UserProcess> checkValue(@Param("aadhar") String aadhar,@Param("pan") String pan);
	
	@Transactional
	@Modifying
	@Query(value = "update UserProcess u set u.opuserid =:opuserid, u.opusername =:opusername, u.opusercomment =:opusercomment, u.preferedjoingdate =:preferedjoingdate, u.userconformation =:userconformation, u.operationdate =:operationdate where u.id =:id", nativeQuery = true)
	Integer updateOperationData(@Param("opuserid") Integer opuserid, @Param("opusername") String opusername,@Param("opusercomment") String opusercomment,@Param("preferedjoingdate") String preferedjoingdate,@Param("userconformation") String userconformation,@Param("operationdate") String operationdate,@Param("id") Integer id);


	@Transactional
	@Modifying
	@Query("update UserProcess u set u.offergenerateddate =:offergenerateddate, u.password =:password,u.userlink =:userlink where u.id =:id")
	Integer offerInitiate(@Param("offergenerateddate") String offergenerateddate, @Param("password") String password,@Param("userlink") String userlink,@Param("id") Integer id);

	

}
