package com.akujas.vcs.web.rest;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akujas.vcs.config.ApplicationProperties;
import com.akujas.vcs.domain.Merchant;
import com.akujas.vcs.service.MerchantService;
import com.akujas.vcs.web.rest.errors.BadRequestAlertException;
import com.akujas.vcs.web.rest.util.HeaderUtil;
import com.akujas.vcs.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Merchant.
 */
@RestController
@RequestMapping("/api")
@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
public class MerchantResource {

    private final Logger log = LoggerFactory.getLogger(MerchantResource.class);

    private static final String ENTITY_NAME = "merchant";

    private final MerchantService merchantService;
    
    @Value("${appdetail.cbMerchantId}")    
    public String cbMerchantId;

    public MerchantResource(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    /**
     * POST  /merchants : Create a new merchant.
     *
     * @param merchant the merchant to create
     * @return the ResponseEntity with status 201 (Created) and with body the new merchant, or with status 400 (Bad Request) if the merchant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/merchants")
    @Timed
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) throws URISyntaxException {
        log.debug("REST request to save Merchant : {}", merchant);
        if (merchant.getId() != null) {
            throw new BadRequestAlertException("A new merchant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        
        Merchant result = merchantService.save(merchant);
        return ResponseEntity.created(new URI("/api/merchants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

      
    /**
     * PUT  /merchants : Updates an existing merchant.
     *
     * @param merchant the merchant to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated merchant,
     * or with status 400 (Bad Request) if the merchant is not valid,
     * or with status 500 (Internal Server Error) if the merchant couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/merchants")
    @Timed
    public ResponseEntity<Merchant> updateMerchant(@RequestBody Merchant merchant) throws URISyntaxException {
        log.debug("REST request to update Merchant : {}", merchant);
        if (merchant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Merchant result = merchantService.save(merchant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, merchant.getId().toString()))
            .body(result);
    }

    /**
     * GET  /merchants : get all the merchants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of merchants in body
     */
    @GetMapping("/merchants")
    @Timed
    public ResponseEntity<List<Merchant>> getAllMerchants(Pageable pageable) {
        log.debug("REST request to get a page of Merchants");
        Page<Merchant> page = merchantService.findAll(pageable);
        
        List<Merchant> appList1 = page.getContent();
        
        List<Merchant> appList=new ArrayList<>(); 
        
		for (Merchant merchant : appList1) {
			if(!merchant.getId().equals(Long.parseLong(cbMerchantId)))
			{
				appList.add(merchant);
			}
		}
        
		
        Page<Merchant> newApplicationsPage = new PageImpl<>(appList, new PageRequest(pageable.getPageNumber(), appList.size()),appList.size());
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(newApplicationsPage, "/api/merchants");
        return new ResponseEntity<>(newApplicationsPage.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /merchants/:id : get the "id" merchant.
     *
     * @param id the id of the merchant to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the merchant, or with status 404 (Not Found)
     */
    @GetMapping("/merchants/{id}")
    @Timed
    public ResponseEntity<Merchant> getMerchant(@PathVariable Long id) {
        log.debug("REST request to get Merchant : {}", id);
        Optional<Merchant> merchant = merchantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(merchant);
    }
    
    @GetMapping("/merchants/fields")
    @Timed
    public List<Object> getAllFields() {
        //log.debug("REST request to get Merchant : {}", id);
        List<Object> merchant = merchantService.getAllFields();
        return merchant;
    }

    /**
     * DELETE  /merchants/:id : delete the "id" merchant.
     *
     * @param id the id of the merchant to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/merchants/{id}")
    @Timed
    public ResponseEntity<Void> deleteMerchant(@PathVariable Long id) {
        log.debug("REST request to delete Merchant : {}", id);
        merchantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
