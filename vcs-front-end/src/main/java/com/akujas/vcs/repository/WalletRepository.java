package com.akujas.vcs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.akujas.vcs.domain.Wallet;


/**
 * Spring Data  repository for the Wallet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	Page<Wallet> findByMid(String merchantid,Pageable pageable);
	
}
