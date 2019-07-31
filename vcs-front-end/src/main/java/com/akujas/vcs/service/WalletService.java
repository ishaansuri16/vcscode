package com.akujas.vcs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akujas.vcs.domain.Wallet;

import java.util.Optional;

/**
 * Service Interface for managing Wallet.
 */
public interface WalletService {

    /**
     * Save a wallet.
     *
     * @param wallet the entity to save
     * @return the persisted entity
     */
    Wallet save(Wallet wallet);

    /**
     * Get all the wallets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Wallet> findAll(Pageable pageable);


    /**
     * Get the "id" wallet.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Wallet> findOne(Long id);

    /**
     * Delete the "id" wallet.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	Page<Wallet>  findWalletByMerchant(Pageable pageable, Long merchantid);
}
