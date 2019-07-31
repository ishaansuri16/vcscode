package com.akujas.vcs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akujas.vcs.domain.Wallet;
import com.akujas.vcs.repository.WalletRepository;
import com.akujas.vcs.service.WalletService;

import java.util.Optional;
/**
 * Service Implementation for managing Wallet.
 */
@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    /**
     * Save a wallet.
     *
     * @param wallet the entity to save
     * @return the persisted entity
     */
    @Override
    public Wallet save(Wallet wallet) {
        log.debug("Request to save Wallet : {}", wallet);        return walletRepository.save(wallet);
    }

    /**
     * Get all the wallets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Wallet> findAll(Pageable pageable) {
        log.debug("Request to get all Wallets");
        return walletRepository.findAll(pageable);
    }


    /**
     * Get one wallet by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Wallet> findOne(Long id) {
        log.debug("Request to get Wallet : {}", id);
        return walletRepository.findById(id);
    }

    /**
     * Delete the wallet by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Wallet : {}", id);
        walletRepository.deleteById(id);
    }

	@Override
	public Page<Wallet> findWalletByMerchant(Pageable pageable, Long merchantid) {
		return walletRepository.findByMid(""+merchantid,pageable);
	}

	
}
