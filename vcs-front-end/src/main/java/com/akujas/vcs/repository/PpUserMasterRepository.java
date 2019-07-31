package com.akujas.vcs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akujas.vcs.domain.PpUserMaster;
import com.akujas.vcs.domain.PpUserMaster;


/**
 * Spring Data  repository for the PpUserMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PpUserMasterRepository extends JpaRepository<PpUserMaster, Long> {
	
	/*
	 * @Query(value = "select * from pp_user_master where ?1 like %?2%", nativeQuery
	 * = true) Page<PpUserMaster> findAllByProperty(String property, String
	 * matchString, Pageable pageable);
	 */
	Page<PpUserMaster> findByNameContaining(String pattern, Pageable pageable);
	Page<PpUserMaster> findByEmailContaining(String pattern, Pageable pageable);
	Page<PpUserMaster> findByMobileContaining(String pattern, Pageable pageable);
	Page<PpUserMaster> findByParam1Containing(String pattern, Pageable pageable);
	Page<PpUserMaster> findByParam2Containing(String pattern, Pageable pageable);
	Page<PpUserMaster> findByParam3Containing(String pattern, Pageable pageable);
	
}
