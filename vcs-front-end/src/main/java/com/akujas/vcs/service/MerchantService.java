package com.akujas.vcs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akujas.vcs.domain.Merchant;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Merchant.
 */
public interface MerchantService {

    /**
     * Save a merchant.
     *
     * @param merchant the entity to save
     * @return the persisted entity
     */
    Merchant save(Merchant merchant);

    /**
     * Get all the merchants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Merchant> findAll(Pageable pageable);


    /**
     * Get the "id" merchant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Merchant> findOne(Long id);

    /**
     * Delete the "id" merchant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    public List<Object> getAllFields();
}
