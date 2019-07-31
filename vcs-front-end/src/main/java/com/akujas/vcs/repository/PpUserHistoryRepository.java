package com.akujas.vcs.repository;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.joda.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akujas.vcs.domain.PpUserHistory;
import com.akujas.vcs.domain.enumeration.TransactionStatus;
import com.akujas.vcs.domain.enumeration.TransactionType;


/**
 * Spring Data  repository for the PpUserHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PpUserHistoryRepository extends JpaRepository<PpUserHistory, Long> {
	
	
	//@Query(value = "select pp from PpUserHistory pp where pp.||:property like %:matchString%")
	//Page<PpUserHistory> findAllByProperty(@Param("property") String property, @Param("matchString") String matchString, Pageable pageable);
	Page<PpUserHistory> findByMidContaining(String pattern, Pageable pageable);
	Page<PpUserHistory> findByDescriptionContaining(String pattern, Pageable pageable);
	Page<PpUserHistory> findBySourceContaining(String pattern, Pageable pageable);
	Page<PpUserHistory> findByOrderIdContaining(String pattern, Pageable pageable);
	Page<PpUserHistory> findByParam1Containing(String pattern, Pageable pageable);
	Page<PpUserHistory> findByParam2Containing(String pattern, Pageable pageable);
	Page<PpUserHistory> findByParam3Containing(String pattern, Pageable pageable);
	Page<PpUserHistory> findByUserIdContaining(String pattern, Pageable pageable);
	Page<PpUserHistory> findByTransactionDateContaining(String matchString, Pageable pageable);
	
	Page<PpUserHistory> findByTransactionType(TransactionType tt, Pageable pageable);
	Page<PpUserHistory> findByTransactionStatus(TransactionStatus ts, Pageable pageable);
	
	

}