package com.akujas.vcs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akujas.vcs.domain.WalletHistory;
import com.akujas.vcs.repository.WalletHistoryRepository;
import com.akujas.vcs.service.WalletHistoryService;

import java.util.Optional;
/**
 * Service Implementation for managing WalletHistory.
 */
@Service
@Transactional
public class WalletHistoryServiceImpl implements WalletHistoryService {

    private final Logger log = LoggerFactory.getLogger(WalletHistoryServiceImpl.class);

    private final WalletHistoryRepository walletHistoryRepository;

    public WalletHistoryServiceImpl(WalletHistoryRepository walletHistoryRepository) {
        this.walletHistoryRepository = walletHistoryRepository;
    }

    /**
     * Save a walletHistory.
     *
     * @param walletHistory the entity to save
     * @return the persisted entity
     */
    @Override
    public WalletHistory save(WalletHistory walletHistory) {
        log.debug("Request to save WalletHistory : {}", walletHistory);        return walletHistoryRepository.save(walletHistory);
    }

    /**
     * Get all the walletHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WalletHistory> findAll(Pageable pageable) {
        log.debug("Request to get all WalletHistories");
        return walletHistoryRepository.findAll(pageable);
    }


    /**
     * Get one walletHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WalletHistory> findOne(Long id) {
        log.debug("Request to get WalletHistory : {}", id);
        return walletHistoryRepository.findById(id);
    }

    /**
     * Delete the walletHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WalletHistory : {}", id);
        walletHistoryRepository.deleteById(id);
    }
}
