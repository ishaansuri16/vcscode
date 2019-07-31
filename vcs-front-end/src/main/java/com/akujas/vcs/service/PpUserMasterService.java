package com.akujas.vcs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.akujas.vcs.domain.PpUserHistory;
import com.akujas.vcs.domain.PpUserMaster;

import java.util.Optional;

/**
 * Service Interface for managing PpUserMaster.
 */
public interface PpUserMasterService {

    /**
     * Save a ppUserMaster.
     *
     * @param ppUserMaster the entity to save
     * @return the persisted entity
     */
    PpUserMaster save(PpUserMaster ppUserMaster);

    /**
     * Get all the ppUserMasters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PpUserMaster> findAll(Pageable pageable);
    
    Page<PpUserMaster> findAllByProperty(String property, String matchString, Pageable pageable);

    /**
     * Get the "id" ppUserMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PpUserMaster> findOne(Long id);

    /**
     * Delete the "id" ppUserMaster.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
