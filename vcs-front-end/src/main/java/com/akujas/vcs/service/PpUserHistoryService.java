package com.akujas.vcs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akujas.vcs.domain.PpUserHistory;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PpUserHistory.
 */
public interface PpUserHistoryService {

    /**
     * Save a ppUserHistory.
     *
     * @param ppUserHistory the entity to save
     * @return the persisted entity
     */
    PpUserHistory save(PpUserHistory ppUserHistory);

    /**
     * Get all the ppUserHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PpUserHistory> findAll(Pageable pageable);
    
    Page<PpUserHistory> findAllByProperty(String property, String matchString, Pageable pageable);


    /**
     * Get the "id" ppUserHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PpUserHistory> findOne(Long id);

    /**
     * Delete the "id" ppUserHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    
}
