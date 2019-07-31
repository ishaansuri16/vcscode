package com.akujas.vcs.service.impl;

import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akujas.vcs.domain.PpUserHistory;
import com.akujas.vcs.domain.enumeration.PpUserHistoryProperty;
import com.akujas.vcs.domain.enumeration.TransactionStatus;
import com.akujas.vcs.domain.enumeration.TransactionType;
import com.akujas.vcs.repository.PpUserHistoryRepository;
import com.akujas.vcs.service.PpUserHistoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing PpUserHistory.
 */
@Service
@Transactional
public class PpUserHistoryServiceImpl implements PpUserHistoryService {

    private final Logger log = LoggerFactory.getLogger(PpUserHistoryServiceImpl.class);

    private final PpUserHistoryRepository ppUserHistoryRepository;
    private PpUserHistoryProperty ppProp;

    public PpUserHistoryServiceImpl(PpUserHistoryRepository ppUserHistoryRepository) {
        this.ppUserHistoryRepository = ppUserHistoryRepository;
    }

    /**
     * Save a ppUserHistory.
     *
     * @param ppUserHistory the entity to save
     * @return the persisted entity
     */
    @Override
    public PpUserHistory save(PpUserHistory ppUserHistory) {
        log.debug("Request to save PpUserHistory : {}", ppUserHistory);        return ppUserHistoryRepository.save(ppUserHistory);
    }

    /**
     * Get all the ppUserHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PpUserHistory> findAll(Pageable pageable) {
        log.debug("Request to get all PpUserHistories");
        return ppUserHistoryRepository.findAll(pageable);
    }


    /**
     * Get one ppUserHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PpUserHistory> findOne(Long id) {
        log.debug("Request to get PpUserHistory : {}", id);
        return ppUserHistoryRepository.findById(id);
    }

    /**
     * Delete the ppUserHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PpUserHistory : {}", id);
        ppUserHistoryRepository.deleteById(id);
    }

	/*
	 * @Override public Page<PpUserHistory> findAllByProperty(String property,
	 * String matchString, Pageable pageable) { return
	 * ppUserHistoryRepository.findAllByProperty(property, matchString, pageable); }
	 */
    @Override
	public Page<PpUserHistory> findAllByProperty(String property, String matchString, Pageable pageable) {
		ppProp = PpUserHistoryProperty.fromString(property);
    	if(ppProp != null) {
	    	switch(ppProp) {
		    	case DESCRIPTION:
		    		return  ppUserHistoryRepository.findByDescriptionContaining(matchString, pageable);
		    		//break;
		    	case MID:
		    		return ppUserHistoryRepository.findByMidContaining(matchString, pageable);
		    		//break;
		    	case ORDER_ID:
		    		return ppUserHistoryRepository.findByOrderIdContaining(matchString, pageable);
		    		//break;
		    	case PARAM1:
		    		return ppUserHistoryRepository.findByParam1Containing(matchString, pageable);
		    		//break;
		    	case PARAM2:
		    		return ppUserHistoryRepository.findByParam2Containing(matchString, pageable);
		    		//break;
		    	case PARAM3:
		    		return ppUserHistoryRepository.findByParam3Containing(matchString, pageable);
		    		//break;
		    	case SOURCE:
		    		return ppUserHistoryRepository.findBySourceContaining(matchString, pageable);
		    		//break;
		    	case TRANSACTION_DATE:
		    		return ppUserHistoryRepository.findByTransactionDateContaining(matchString, pageable);
		    		//break;
		    	case TRANSACTION_STATUS:
		    		TransactionStatus ts = TransactionStatus.getTransactionType(matchString.toUpperCase());
		    		if(ts != null)
		    			return ppUserHistoryRepository.findByTransactionStatus(ts, pageable);
		    		break;
		    	case TRANSACTION_TYPE:
		    		TransactionType tt = TransactionType.getTransactionType(matchString.toUpperCase());
		    		if(tt != null)
		    			return ppUserHistoryRepository.findByTransactionType(tt, pageable);
		    		break;
		    	case USER_ID:
		    		return ppUserHistoryRepository.findByUserIdContaining(matchString, pageable);
		    		//break;
		    	default :
		    		log.debug("Invalid Property");
	    	}
	    }
    	return null;
	}
}
