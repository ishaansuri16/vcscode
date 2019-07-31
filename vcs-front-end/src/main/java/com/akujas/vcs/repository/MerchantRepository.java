package com.akujas.vcs.repository;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.akujas.vcs.domain.Merchant;


/**
 * Spring Data  repository for the Merchant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	
	/*
	 * @Query(value = "SELECT `COLUMN_NAME`"+ "FROM `INFORMATION_SCHEMA`.`COLUMNS` "
	 * + "WHERE `TABLE_SCHEMA`='vcs'" + " AND `TABLE_NAME`='pp_user_master';",
	 * nativeQuery = true)
	 */
	@Query(value = "desc merchant",
			nativeQuery = true)
	List<Object> getAllFields();

}
