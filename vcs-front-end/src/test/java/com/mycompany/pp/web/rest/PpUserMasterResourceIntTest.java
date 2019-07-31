package com.mycompany.pp.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.akujas.vcs.VCSFrontEndApp;
import com.akujas.vcs.domain.PpUserMaster;
import com.akujas.vcs.repository.PpUserMasterRepository;
import com.akujas.vcs.service.PpUserMasterService;
import com.akujas.vcs.web.rest.PpUserMasterResource;
import com.akujas.vcs.web.rest.errors.ExceptionTranslator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.mycompany.pp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PpUserMasterResource REST controller.
 *
 * @see PpUserMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VCSFrontEndApp.class)
public class PpUserMasterResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final Long DEFAULT_PRICE_POINT_VALUE = 1L;
    private static final Long UPDATED_PRICE_POINT_VALUE = 2L;

    private static final String DEFAULT_PARAM_1 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_2 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_3 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_3 = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PpUserMasterRepository ppUserMasterRepository;

    

    @Autowired
    private PpUserMasterService ppUserMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPpUserMasterMockMvc;

    private PpUserMaster ppUserMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PpUserMasterResource ppUserMasterResource = new PpUserMasterResource(ppUserMasterService);
        this.restPpUserMasterMockMvc = MockMvcBuilders.standaloneSetup(ppUserMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PpUserMaster createEntity(EntityManager em) {
        PpUserMaster ppUserMaster = new PpUserMaster()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .pricePointValue(DEFAULT_PRICE_POINT_VALUE)
            .param1(DEFAULT_PARAM_1)
            .param2(DEFAULT_PARAM_2)
            .param3(DEFAULT_PARAM_3)
            .created(DEFAULT_CREATED)
            .modifiedon(DEFAULT_MODIFIEDON);
        return ppUserMaster;
    }

    @Before
    public void initTest() {
        ppUserMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createPpUserMaster() throws Exception {
        int databaseSizeBeforeCreate = ppUserMasterRepository.findAll().size();

        // Create the PpUserMaster
        restPpUserMasterMockMvc.perform(post("/api/pp-user-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ppUserMaster)))
            .andExpect(status().isCreated());

        // Validate the PpUserMaster in the database
        List<PpUserMaster> ppUserMasterList = ppUserMasterRepository.findAll();
        assertThat(ppUserMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PpUserMaster testPpUserMaster = ppUserMasterList.get(ppUserMasterList.size() - 1);
        assertThat(testPpUserMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPpUserMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPpUserMaster.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testPpUserMaster.getPricePointValue()).isEqualTo(DEFAULT_PRICE_POINT_VALUE);
        assertThat(testPpUserMaster.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testPpUserMaster.getParam2()).isEqualTo(DEFAULT_PARAM_2);
        assertThat(testPpUserMaster.getParam3()).isEqualTo(DEFAULT_PARAM_3);
        assertThat(testPpUserMaster.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testPpUserMaster.getModifiedon()).isEqualTo(DEFAULT_MODIFIEDON);
    }

    @Test
    @Transactional
    public void createPpUserMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ppUserMasterRepository.findAll().size();

        // Create the PpUserMaster with an existing ID
        ppUserMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPpUserMasterMockMvc.perform(post("/api/pp-user-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ppUserMaster)))
            .andExpect(status().isBadRequest());

        // Validate the PpUserMaster in the database
        List<PpUserMaster> ppUserMasterList = ppUserMasterRepository.findAll();
        assertThat(ppUserMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPpUserMasters() throws Exception {
        // Initialize the database
        ppUserMasterRepository.saveAndFlush(ppUserMaster);

        // Get all the ppUserMasterList
        restPpUserMasterMockMvc.perform(get("/api/pp-user-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ppUserMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].pricePointValue").value(hasItem(DEFAULT_PRICE_POINT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1.toString())))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2.toString())))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modifiedon").value(hasItem(DEFAULT_MODIFIEDON.toString())));
    }
    

    @Test
    @Transactional
    public void getPpUserMaster() throws Exception {
        // Initialize the database
        ppUserMasterRepository.saveAndFlush(ppUserMaster);

        // Get the ppUserMaster
        restPpUserMasterMockMvc.perform(get("/api/pp-user-masters/{id}", ppUserMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ppUserMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.pricePointValue").value(DEFAULT_PRICE_POINT_VALUE.intValue()))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1.toString()))
            .andExpect(jsonPath("$.param2").value(DEFAULT_PARAM_2.toString()))
            .andExpect(jsonPath("$.param3").value(DEFAULT_PARAM_3.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modifiedon").value(DEFAULT_MODIFIEDON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPpUserMaster() throws Exception {
        // Get the ppUserMaster
        restPpUserMasterMockMvc.perform(get("/api/pp-user-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePpUserMaster() throws Exception {
        // Initialize the database
        ppUserMasterService.save(ppUserMaster);

        int databaseSizeBeforeUpdate = ppUserMasterRepository.findAll().size();

        // Update the ppUserMaster
        PpUserMaster updatedPpUserMaster = ppUserMasterRepository.findById(ppUserMaster.getId()).get();
        // Disconnect from session so that the updates on updatedPpUserMaster are not directly saved in db
        em.detach(updatedPpUserMaster);
        updatedPpUserMaster
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .pricePointValue(UPDATED_PRICE_POINT_VALUE)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .created(UPDATED_CREATED)
            .modifiedon(UPDATED_MODIFIEDON);

        restPpUserMasterMockMvc.perform(put("/api/pp-user-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPpUserMaster)))
            .andExpect(status().isOk());

        // Validate the PpUserMaster in the database
        List<PpUserMaster> ppUserMasterList = ppUserMasterRepository.findAll();
        assertThat(ppUserMasterList).hasSize(databaseSizeBeforeUpdate);
        PpUserMaster testPpUserMaster = ppUserMasterList.get(ppUserMasterList.size() - 1);
        assertThat(testPpUserMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPpUserMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPpUserMaster.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testPpUserMaster.getPricePointValue()).isEqualTo(UPDATED_PRICE_POINT_VALUE);
        assertThat(testPpUserMaster.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testPpUserMaster.getParam2()).isEqualTo(UPDATED_PARAM_2);
        assertThat(testPpUserMaster.getParam3()).isEqualTo(UPDATED_PARAM_3);
        assertThat(testPpUserMaster.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testPpUserMaster.getModifiedon()).isEqualTo(UPDATED_MODIFIEDON);
    }

    @Test
    @Transactional
    public void updateNonExistingPpUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = ppUserMasterRepository.findAll().size();

        // Create the PpUserMaster

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPpUserMasterMockMvc.perform(put("/api/pp-user-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ppUserMaster)))
            .andExpect(status().isBadRequest());

        // Validate the PpUserMaster in the database
        List<PpUserMaster> ppUserMasterList = ppUserMasterRepository.findAll();
        assertThat(ppUserMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePpUserMaster() throws Exception {
        // Initialize the database
        ppUserMasterService.save(ppUserMaster);

        int databaseSizeBeforeDelete = ppUserMasterRepository.findAll().size();

        // Get the ppUserMaster
        restPpUserMasterMockMvc.perform(delete("/api/pp-user-masters/{id}", ppUserMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PpUserMaster> ppUserMasterList = ppUserMasterRepository.findAll();
        assertThat(ppUserMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PpUserMaster.class);
        PpUserMaster ppUserMaster1 = new PpUserMaster();
        ppUserMaster1.setId(1L);
        PpUserMaster ppUserMaster2 = new PpUserMaster();
        ppUserMaster2.setId(ppUserMaster1.getId());
        assertThat(ppUserMaster1).isEqualTo(ppUserMaster2);
        ppUserMaster2.setId(2L);
        assertThat(ppUserMaster1).isNotEqualTo(ppUserMaster2);
        ppUserMaster1.setId(null);
        assertThat(ppUserMaster1).isNotEqualTo(ppUserMaster2);
    }
}
