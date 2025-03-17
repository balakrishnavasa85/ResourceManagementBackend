package com.application.hrms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.hrms.POJO.Tds;
@Repository
public interface TdsDao extends JpaRepository<Tds,Integer>{
	
	 @Query("SELECT t FROM Tds t WHERE :amount >= t.fromamount AND (t.toamount IS NULL OR :amount <= t.toamount)")
	 Optional<Tds> findTaxSlabByAmount(@Param("amount") Integer amount);

}
