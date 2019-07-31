package com.akujas.vcs.web.rest;

import com.akujas.vcs.domain.PpUserHistory;
import com.akujas.vcs.service.PpUserHistoryService;
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
 * REST controller for managing PpUserHistory.
 */
@RestController
@RequestMapping("/api")
public class PpUserHistoryResource {

    private final Logger log = LoggerFactory.getLogger(PpUserHistoryResource.class);

    private static final String ENTITY_NAME = "ppUserHistory";

    private final PpUserHistoryService ppUserHistoryService;

    public PpUserHistoryResource(PpUserHistoryService ppUserHistoryService) {
        this.ppUserHistoryService = ppUserHistoryService;
    }

    /**
     * POST  /pp-user-histories : Create a new ppUserHistory.
     *
     * @param ppUserHistory the ppUserHistory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ppUserHistory, or with status 400 (Bad Request) if the ppUserHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pp-user-histories")
    @Timed
    public ResponseEntity<PpUserHistory> createPpUserHistory(@RequestBody PpUserHistory ppUserHistory) throws URISyntaxException {
        log.debug("REST request to save PpUserHistory : {}", ppUserHistory);
        if (ppUserHistory.getId() != null) {
            throw new BadRequestAlertException("A new ppUserHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PpUserHistory result = ppUserHistoryService.save(ppUserHistory);
        return ResponseEntity.created(new URI("/api/pp-user-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pp-user-histories : Updates an existing ppUserHistory.
     *
     * @param ppUserHistory the ppUserHistory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ppUserHistory,
     * or with status 400 (Bad Request) if the ppUserHistory is not valid,
     * or with status 500 (Internal Server Error) if the ppUserHistory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pp-user-histories")
    @Timed
    public ResponseEntity<PpUserHistory> updatePpUserHistory(@RequestBody PpUserHistory ppUserHistory) throws URISyntaxException {
        log.debug("REST request to update PpUserHistory : {}", ppUserHistory);
        if (ppUserHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PpUserHistory result = ppUserHistoryService.save(ppUserHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ppUserHistory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pp-user-histories : get all the ppUserHistories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ppUserHistories in body
     */
    @GetMapping("/pp-user-histories")
    @Timed
    public ResponseEntity<List<PpUserHistory>> getAllPpUserHistories(Pageable pageable) {
        log.debug("REST request to get a page of PpUserHistories");
        Page<PpUserHistory> page = ppUserHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pp-user-histories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/pp-user-histories/{prop}/{str}")
    @Timed
    public ResponseEntity<List<PpUserHistory>> getAllPpUserHistoriesMatching(@PathVariable String prop, @PathVariable String str, Pageable pageable) {
        log.debug("REST request to get a page of PpUserHistories");
        Page<PpUserHistory> page = ppUserHistoryService.findAllByProperty(prop, str, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pp-user-histories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		/*
		 * return ResponseEntity.ok()
		 * .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, "List sent"))
		 * .body(page);
		 */
    }

    /**
     * GET  /pp-user-histories/:id : get the "id" ppUserHistory.
     *
     * @param id the id of the ppUserHistory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ppUserHistory, or with status 404 (Not Found)
     */
    @GetMapping("/pp-user-histories/{id}")
    @Timed
    public ResponseEntity<PpUserHistory> getPpUserHistory(@PathVariable Long id) {
        log.debug("REST request to get PpUserHistory : {}", id);
        Optional<PpUserHistory> ppUserHistory = ppUserHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ppUserHistory);
    }

    /**
     * DELETE  /pp-user-histories/:id : delete the "id" ppUserHistory.
     *
     * @param id the id of the ppUserHistory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pp-user-histories/{id}")
    @Timed
    public ResponseEntity<Void> deletePpUserHistory(@PathVariable Long id) {
        log.debug("REST request to delete PpUserHistory : {}", id);
        ppUserHistoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
