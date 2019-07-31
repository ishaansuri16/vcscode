package com.akujas.vcs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akujas.vcs.domain.WalletHistory;

import java.util.Optional;

/**
 * Service Interface for managing WalletHistory.
 */
public interface WalletHistoryService {

    /**
     * Save a walletHistory.
     *
     * @param walletHistory the entity to save
     * @return the persisted entity
     */
    WalletHistory save(WalletHistory walletHistory);

    /**
     * Get all the walletHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WalletHistory> findAll(Pageable pageable);


    /**
     * Get the "id" walletHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WalletHistory> findOne(Long id);

    /**
     * Delete the "id" walletHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
