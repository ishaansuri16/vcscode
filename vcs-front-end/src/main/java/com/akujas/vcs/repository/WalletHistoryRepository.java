package com.akujas.vcs.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.akujas.vcs.domain.WalletHistory;


/**
 * Spring Data  repository for the WalletHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletHistoryRepository extends JpaRepository<WalletHistory, Long> {

}
