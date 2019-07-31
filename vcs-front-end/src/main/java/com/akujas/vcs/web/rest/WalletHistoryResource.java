package com.akujas.vcs.web.rest;

import com.akujas.vcs.domain.WalletHistory;
import com.akujas.vcs.service.WalletHistoryService;
import com.akujas.vcs.web.rest.errors.BadRequestAlertException;
import com.akujas.vcs.web.rest.util.HeaderUtil;
import com.akujas.vcs.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WalletHistory.
 */
@RestController
@RequestMapping("/api")
public class WalletHistoryResource {

    private final Logger log = LoggerFactory.getLogger(WalletHistoryResource.class);

    private static final String ENTITY_NAME = "walletHistory";

    private final WalletHistoryService walletHistoryService;

    public WalletHistoryResource(WalletHistoryService walletHistoryService) {
        this.walletHistoryService = walletHistoryService;
    }

    /**
     * POST  /wallet-histories : Create a new walletHistory.
     *
     * @param walletHistory the walletHistory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new walletHistory, or with status 400 (Bad Request) if the walletHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wallet-histories")
    @Timed
    public ResponseEntity<WalletHistory> createWalletHistory(@RequestBody WalletHistory walletHistory) throws URISyntaxException {
        log.debug("REST request to save WalletHistory : {}", walletHistory);
        if (walletHistory.getId() != null) {
            throw new BadRequestAlertException("A new walletHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletHistory result = walletHistoryService.save(walletHistory);
        return ResponseEntity.created(new URI("/api/wallet-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wallet-histories : Updates an existing walletHistory.
     *
     * @param walletHistory the walletHistory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated walletHistory,
     * or with status 400 (Bad Request) if the walletHistory is not valid,
     * or with status 500 (Internal Server Error) if the walletHistory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wallet-histories")
    @Timed
    public ResponseEntity<WalletHistory> updateWalletHistory(@RequestBody WalletHistory walletHistory) throws URISyntaxException {
        log.debug("REST request to update WalletHistory : {}", walletHistory);
        if (walletHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletHistory result = walletHistoryService.save(walletHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, walletHistory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wallet-histories : get all the walletHistories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of walletHistories in body
     */
    @GetMapping("/wallet-histories")
    @Timed
    public ResponseEntity<List<WalletHistory>> getAllWalletHistories(Pageable pageable) {
        log.debug("REST request to get a page of WalletHistories");
        Page<WalletHistory> page = walletHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wallet-histories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /wallet-histories/:id : get the "id" walletHistory.
     *
     * @param id the id of the walletHistory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the walletHistory, or with status 404 (Not Found)
     */
    @GetMapping("/wallet-histories/{id}")
    @Timed
    public ResponseEntity<WalletHistory> getWalletHistory(@PathVariable Long id) {
        log.debug("REST request to get WalletHistory : {}", id);
        Optional<WalletHistory> walletHistory = walletHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletHistory);
    }

    /**
     * DELETE  /wallet-histories/:id : delete the "id" walletHistory.
     *
     * @param id the id of the walletHistory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wallet-histories/{id}")
    @Timed
    public ResponseEntity<Void> deleteWalletHistory(@PathVariable Long id) {
        log.debug("REST request to delete WalletHistory : {}", id);
        walletHistoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
