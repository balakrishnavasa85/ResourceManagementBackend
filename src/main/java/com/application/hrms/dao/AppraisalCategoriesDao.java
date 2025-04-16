package com.application.hrms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.wrapper.DepartmentWrapper;

@Repository
public interface AppraisalCategoriesDao extends JpaRepository<AppraisalCategories, Integer> {

	@Query("select u from AppraisalCategories u where u.name=:name")
	AppraisalCategories findByName(String name);

	@Transactional
    @Modifying
    @Query("update AppraisalCategories u set u.status=:status where u.category_id =:id")
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
	
	@Query("select u from AppraisalCategories u where u.status = 'y'")
	List<AppraisalCategories> getAllActive();
	
	@Query("select u from AppraisalCategories u  where u.category_id =:id")
	List<AppraisalCategories> getValuesById(@Param("id") Integer id);
}
