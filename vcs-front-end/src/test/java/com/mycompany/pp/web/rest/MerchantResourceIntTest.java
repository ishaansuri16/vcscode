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
import com.akujas.vcs.domain.Merchant;
import com.akujas.vcs.repository.MerchantRepository;
import com.akujas.vcs.service.MerchantService;
import com.akujas.vcs.web.rest.MerchantResource;
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
 * Test class for the MerchantResource REST controller.
 *
 * @see MerchantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VCSFrontEndApp.class)
public class MerchantResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MID = "AAAAAAAAAA";
    private static final String UPDATED_MID = "BBBBBBBBBB";

    private static final String DEFAULT_PARAMS = "AAAAAAAAAA";
    private static final String UPDATED_PARAMS = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_1 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_2 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_3 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_3 = "BBBBBBBBBB";

    private static final String DEFAULT_MID_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_MID_SECRET = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISENABLED = false;
    private static final Boolean UPDATED_ISENABLED = true;

    @Autowired
    private MerchantRepository merchantRepository;

    

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMerchantMockMvc;

    private Merchant merchant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MerchantResource merchantResource = new MerchantResource(merchantService);
        this.restMerchantMockMvc = MockMvcBuilders.standaloneSetup(merchantResource)
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
    public static Merchant createEntity(EntityManager em) {
        Merchant merchant = new Merchant()
            .name(DEFAULT_NAME)
            .displayName(DEFAULT_DISPLAY_NAME)
            .description(DEFAULT_DESCRIPTION)
            .mid(DEFAULT_MID)
            .params(DEFAULT_PARAMS)
            .param1(DEFAULT_PARAM_1)
            .param2(DEFAULT_PARAM_2)
            .param3(DEFAULT_PARAM_3)
            .midSecret(DEFAULT_MID_SECRET)
            .createdBy(DEFAULT_CREATED_BY)
            .created(DEFAULT_CREATED)
            .modifiedon(DEFAULT_MODIFIEDON)
            .pincode(DEFAULT_PINCODE)
            .address(DEFAULT_ADDRESS)
            .isenabled(DEFAULT_ISENABLED);
        return merchant;
    }

    @Before
    public void initTest() {
        merchant = createEntity(em);
    }

    @Test
    @Transactional
    public void createMerchant() throws Exception {
        int databaseSizeBeforeCreate = merchantRepository.findAll().size();

        // Create the Merchant
        restMerchantMockMvc.perform(post("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchant)))
            .andExpect(status().isCreated());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeCreate + 1);
        Merchant testMerchant = merchantList.get(merchantList.size() - 1);
        assertThat(testMerchant.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMerchant.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testMerchant.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMerchant.getMid()).isEqualTo(DEFAULT_MID);
        assertThat(testMerchant.getParams()).isEqualTo(DEFAULT_PARAMS);
        assertThat(testMerchant.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testMerchant.getParam2()).isEqualTo(DEFAULT_PARAM_2);
        assertThat(testMerchant.getParam3()).isEqualTo(DEFAULT_PARAM_3);
        assertThat(testMerchant.getMidSecret()).isEqualTo(DEFAULT_MID_SECRET);
        assertThat(testMerchant.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMerchant.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testMerchant.getModifiedon()).isEqualTo(DEFAULT_MODIFIEDON);
        assertThat(testMerchant.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testMerchant.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testMerchant.isIsenabled()).isEqualTo(DEFAULT_ISENABLED);
    }

    @Test
    @Transactional
    public void createMerchantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = merchantRepository.findAll().size();

        // Create the Merchant with an existing ID
        merchant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMerchantMockMvc.perform(post("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchant)))
            .andExpect(status().isBadRequest());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMerchants() throws Exception {
        // Initialize the database
        merchantRepository.saveAndFlush(merchant);

        // Get all the merchantList
        restMerchantMockMvc.perform(get("/api/merchants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(merchant.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].mid").value(hasItem(DEFAULT_MID.toString())))
            .andExpect(jsonPath("$.[*].params").value(hasItem(DEFAULT_PARAMS.toString())))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1.toString())))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2.toString())))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3.toString())))
            .andExpect(jsonPath("$.[*].midSecret").value(hasItem(DEFAULT_MID_SECRET.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modifiedon").value(hasItem(DEFAULT_MODIFIEDON.toString())))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].isenabled").value(hasItem(DEFAULT_ISENABLED.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getMerchant() throws Exception {
        // Initialize the database
        merchantRepository.saveAndFlush(merchant);

        // Get the merchant
        restMerchantMockMvc.perform(get("/api/merchants/{id}", merchant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(merchant.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.mid").value(DEFAULT_MID.toString()))
            .andExpect(jsonPath("$.params").value(DEFAULT_PARAMS.toString()))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1.toString()))
            .andExpect(jsonPath("$.param2").value(DEFAULT_PARAM_2.toString()))
            .andExpect(jsonPath("$.param3").value(DEFAULT_PARAM_3.toString()))
            .andExpect(jsonPath("$.midSecret").value(DEFAULT_MID_SECRET.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modifiedon").value(DEFAULT_MODIFIEDON.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.isenabled").value(DEFAULT_ISENABLED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMerchant() throws Exception {
        // Get the merchant
        restMerchantMockMvc.perform(get("/api/merchants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMerchant() throws Exception {
        // Initialize the database
        merchantService.save(merchant);

        int databaseSizeBeforeUpdate = merchantRepository.findAll().size();

        // Update the merchant
        Merchant updatedMerchant = merchantRepository.findById(merchant.getId()).get();
        // Disconnect from session so that the updates on updatedMerchant are not directly saved in db
        em.detach(updatedMerchant);
        updatedMerchant
            .name(UPDATED_NAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .description(UPDATED_DESCRIPTION)
            .mid(UPDATED_MID)
            .params(UPDATED_PARAMS)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .midSecret(UPDATED_MID_SECRET)
            .createdBy(UPDATED_CREATED_BY)
            .created(UPDATED_CREATED)
            .modifiedon(UPDATED_MODIFIEDON)
            .pincode(UPDATED_PINCODE)
            .address(UPDATED_ADDRESS)
            .isenabled(UPDATED_ISENABLED);

        restMerchantMockMvc.perform(put("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMerchant)))
            .andExpect(status().isOk());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeUpdate);
        Merchant testMerchant = merchantList.get(merchantList.size() - 1);
        assertThat(testMerchant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMerchant.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testMerchant.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMerchant.getMid()).isEqualTo(UPDATED_MID);
        assertThat(testMerchant.getParams()).isEqualTo(UPDATED_PARAMS);
        assertThat(testMerchant.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testMerchant.getParam2()).isEqualTo(UPDATED_PARAM_2);
        assertThat(testMerchant.getParam3()).isEqualTo(UPDATED_PARAM_3);
        assertThat(testMerchant.getMidSecret()).isEqualTo(UPDATED_MID_SECRET);
        assertThat(testMerchant.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMerchant.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testMerchant.getModifiedon()).isEqualTo(UPDATED_MODIFIEDON);
        assertThat(testMerchant.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testMerchant.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testMerchant.isIsenabled()).isEqualTo(UPDATED_ISENABLED);
    }

    @Test
    @Transactional
    public void updateNonExistingMerchant() throws Exception {
        int databaseSizeBeforeUpdate = merchantRepository.findAll().size();

        // Create the Merchant

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMerchantMockMvc.perform(put("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchant)))
            .andExpect(status().isBadRequest());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMerchant() throws Exception {
        // Initialize the database
        merchantService.save(merchant);

        int databaseSizeBeforeDelete = merchantRepository.findAll().size();

        // Get the merchant
        restMerchantMockMvc.perform(delete("/api/merchants/{id}", merchant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Merchant.class);
        Merchant merchant1 = new Merchant();
        merchant1.setId(1L);
        Merchant merchant2 = new Merchant();
        merchant2.setId(merchant1.getId());
        assertThat(merchant1).isEqualTo(merchant2);
        merchant2.setId(2L);
        assertThat(merchant1).isNotEqualTo(merchant2);
        merchant1.setId(null);
        assertThat(merchant1).isNotEqualTo(merchant2);
    }
}
