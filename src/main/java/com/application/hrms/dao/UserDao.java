package com.application.hrms.dao;

import com.application.hrms.POJO.User;
import javax.transaction.Transactional;

import com.application.hrms.wrapper.UserWrapper;

import org.springframework.data.jpa.repository.EntityGraph;
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
public interface UserDao extends JpaRepository<User, Integer> {
	
//	@Query("select new com.application.hrms.wrapper.UserWrapper(u.id , u.name , u.email , u.contactNumber , u.status, u.role) from User u")
//	List<UserWrapper> getAllUser();

    @Transactional
    @Modifying
    @Query(value="update User u set u.status=:status where u.id =:id", nativeQuery=true)
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
    
    @Transactional
    @Modifying
    @Query(value="update User u set u.login=:login where u.id =:id", nativeQuery=true)
    Integer updateLoginStatus(@Param("login") String login, @Param("id") Integer id);
    
    @Query(value="select u.email from User u where u.role = 'admin'", nativeQuery=true)
    List<String> getAllAdmin();

    User findByEmail(String email);    
    
    @Query(value="select * from User u where u.email=:email", nativeQuery=true)
    User findByEmailId(String email);

//    @EntityGraph(attributePaths = {"identityDetails", "department", "designation", "deductiongroup"})
    @Query("select u  from User u  where u.id =:id")
	List<User> getUserById(@Param("id") Integer id);
    
//    @EntityGraph(attributePaths = {"identityDetails", "department", "designation", "deductiongroup"})
    @Query("select u  from User u  where u.manager = 'y'")
	List<User> findAllAdmins();
    
    @Query("select u  from User u  where u.name =:user")
    User findByUsername(@Param("user") String user);
    

    @Query("select u  from User u  where u.id =:id")
	User getUserDetailById(@Param("id") Integer id);
    
    @Query(value = "SELECT u.id, u.deductiongroupid, u.basicpa FROM User u WHERE u.id = :id", nativeQuery=true)
    List<Object[]> findUserSalaryDetailsById(@Param("id") Integer id);
    
    @Transactional
    @Modifying
    @Query(value="update User u set u.profilepic=:filepath where u.id =:userId", nativeQuery=true)
    Integer saveProfilepic(@Param("userId") Integer userId, @Param("filepath") String filepath);
    
    @Query("select u.name from User u where u.id =:reporting")
    String getReporterName(@Param("reporting") Integer reporting);
    
//    @EntityGraph(attributePaths = {"identityDetails", "department", "designation", "deductiongroup"})
    List<User> findAll();
 

//    @EntityGraph(attributePaths = {"identityDetails", "department", "designation", "deductionGroup"})
//    List<User> getUserById(Integer id);

//    @EntityGraph(attributePaths = {"identityDetails", "department", "designation", "deductionGroup"})
//    List<User> findAllAdmins();

//    @Query("SELECT u.reportername FROM User u WHERE u.id = :id")
//    String getReporterName(@Param("id") Integer id);

    
}

