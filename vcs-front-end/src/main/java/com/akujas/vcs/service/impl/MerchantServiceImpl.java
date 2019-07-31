package com.akujas.vcs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akujas.vcs.domain.Merchant;
import com.akujas.vcs.repository.MerchantRepository;
import com.akujas.vcs.service.MerchantService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Merchant.
 */
@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    private final Logger log = LoggerFactory.getLogger(MerchantServiceImpl.class);

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    /**
     * Save a merchant.
     *
     * @param merchant the entity to save
     * @return the persisted entity
     */
    @Override
    public Merchant save(Merchant merchant) {
        log.debug("Request to save Merchant : {}", merchant);        return merchantRepository.save(merchant);
    }

    /**
     * Get all the merchants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Merchant> findAll(Pageable pageable) {
        log.debug("Request to get all Merchants");
        return merchantRepository.findAll(pageable);
    }


    /**
     * Get one merchant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Merchant> findOne(Long id) {
        log.debug("Request to get Merchant : {}", id);
        return merchantRepository.findById(id);
    }

    /**
     * Delete the merchant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Merchant : {}", id);
        merchantRepository.deleteById(id);
    }

    @Override
    public List<Object> getAllFields() {
    	return merchantRepository.getAllFields();
    }
}
