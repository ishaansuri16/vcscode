package com.akujas.vcs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akujas.vcs.domain.PpUserMaster;
import com.akujas.vcs.domain.enumeration.PpUserHistoryProperty;
import com.akujas.vcs.domain.enumeration.PpUserMasterProperty;
import com.akujas.vcs.repository.PpUserMasterRepository;
import com.akujas.vcs.service.PpUserMasterService;

import java.util.Optional;
/**
 * Service Implementation for managing PpUserMaster.
 */
@Service
@Transactional
public class PpUserMasterServiceImpl implements PpUserMasterService {

    private final Logger log = LoggerFactory.getLogger(PpUserMasterServiceImpl.class);

    private final PpUserMasterRepository ppUserMasterRepository;
    private PpUserMasterProperty ppProp;

    public PpUserMasterServiceImpl(PpUserMasterRepository ppUserMasterRepository) {
        this.ppUserMasterRepository = ppUserMasterRepository;
    }

    /**
     * Save a ppUserMaster.
     *
     * @param ppUserMaster the entity to save
     * @return the persisted entity
     */
    @Override
    public PpUserMaster save(PpUserMaster ppUserMaster) {
        log.debug("Request to save PpUserMaster : {}", ppUserMaster);        return ppUserMasterRepository.save(ppUserMaster);
    }

    /**
     * Get all the ppUserMasters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PpUserMaster> findAll(Pageable pageable) {
        log.debug("Request to get all PpUserMasters");
        return ppUserMasterRepository.findAll(pageable);
    }


    /**
     * Get one ppUserMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PpUserMaster> findOne(Long id) {
        log.debug("Request to get PpUserMaster : {}", id);
        return ppUserMasterRepository.findById(id);
    }

    /**
     * Delete the ppUserMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PpUserMaster : {}", id);
        ppUserMasterRepository.deleteById(id);
    }

	@Override
	public Page<PpUserMaster> findAllByProperty(String property, String matchString, Pageable pageable) {
		ppProp = PpUserMasterProperty.fromString(property);
    	if(ppProp != null) {
	    	switch(ppProp) {
		    	case EMAIL:
		    		return  ppUserMasterRepository.findByEmailContaining(matchString, pageable);
		    		//break;
		    	case MOBILE:
		    		return ppUserMasterRepository.findByMobileContaining(matchString, pageable);
		    		//break;
		    	case NAME:
		    		return ppUserMasterRepository.findByNameContaining(matchString, pageable);
		    		//break;
		    	case PARAM1:
		    		return ppUserMasterRepository.findByParam1Containing(matchString, pageable);
		    		//break;
		    	case PARAM2:
		    		return ppUserMasterRepository.findByParam2Containing(matchString, pageable);
		    		//break;
		    	case PARAM3:
		    		return ppUserMasterRepository.findByParam3Containing(matchString, pageable);
		    		//break;
		    	default :
		    		log.debug("Invalid Property");
	    	}
	    }
    	return null;
	}
}
