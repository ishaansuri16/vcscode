package com.akujas.vcs.web.rest;

import com.akujas.vcs.domain.PpUserHistory;
import com.akujas.vcs.domain.PpUserMaster;
import com.akujas.vcs.service.PpUserMasterService;
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
 * REST controller for managing PpUserMaster.
 */
@RestController
@RequestMapping("/api")
public class PpUserMasterResource {

    private final Logger log = LoggerFactory.getLogger(PpUserMasterResource.class);

    private static final String ENTITY_NAME = "ppUserMaster";

    private final PpUserMasterService ppUserMasterService;

    public PpUserMasterResource(PpUserMasterService ppUserMasterService) {
        this.ppUserMasterService = ppUserMasterService;
    }

    /**
     * POST  /pp-user-masters : Create a new ppUserMaster.
     *
     * @param ppUserMaster the ppUserMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ppUserMaster, or with status 400 (Bad Request) if the ppUserMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pp-user-masters")
    @Timed
    public ResponseEntity<PpUserMaster> createPpUserMaster(@RequestBody PpUserMaster ppUserMaster) throws URISyntaxException {
        log.debug("REST request to save PpUserMaster : {}", ppUserMaster);
        if (ppUserMaster.getId() != null) {
            throw new BadRequestAlertException("A new ppUserMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PpUserMaster result = ppUserMasterService.save(ppUserMaster);
        return ResponseEntity.created(new URI("/api/pp-user-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pp-user-masters : Updates an existing ppUserMaster.
     *
     * @param ppUserMaster the ppUserMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ppUserMaster,
     * or with status 400 (Bad Request) if the ppUserMaster is not valid,
     * or with status 500 (Internal Server Error) if the ppUserMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pp-user-masters")
    @Timed
    public ResponseEntity<PpUserMaster> updatePpUserMaster(@RequestBody PpUserMaster ppUserMaster) throws URISyntaxException {
        log.debug("REST request to update PpUserMaster : {}", ppUserMaster);
        if (ppUserMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PpUserMaster result = ppUserMasterService.save(ppUserMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ppUserMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pp-user-masters : get all the ppUserMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ppUserMasters in body
     */
    @GetMapping("/pp-user-masters")
    @Timed
    public ResponseEntity<List<PpUserMaster>> getAllPpUserMasters(Pageable pageable) {
        log.debug("REST request to get a page of PpUserMasters");
        Page<PpUserMaster> page = ppUserMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pp-user-masters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pp-user-masters/:id : get the "id" ppUserMaster.
     *
     * @param id the id of the ppUserMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ppUserMaster, or with status 404 (Not Found)
     */
    @GetMapping("/pp-user-masters/{id}")
    @Timed
    public ResponseEntity<PpUserMaster> getPpUserMaster(@PathVariable Long id) {
        log.debug("REST request to get PpUserMaster : {}", id);
        Optional<PpUserMaster> ppUserMaster = ppUserMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ppUserMaster);
    }
    
    @GetMapping("/pp-user-masters/{prop}/{str}")
    @Timed
    public ResponseEntity<List<PpUserMaster>> getAllPpUserMastersMatching(@PathVariable String prop, @PathVariable String str, Pageable pageable) {
        log.debug("REST request to get a page of PpUserMaster");
        Page<PpUserMaster> page = ppUserMasterService.findAllByProperty(prop, str, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pp-user-masters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * DELETE  /pp-user-masters/:id : delete the "id" ppUserMaster.
     *
     * @param id the id of the ppUserMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pp-user-masters/{id}")
    @Timed
    public ResponseEntity<Void> deletePpUserMaster(@PathVariable Long id) {
        log.debug("REST request to delete PpUserMaster : {}", id);
        ppUserMasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
