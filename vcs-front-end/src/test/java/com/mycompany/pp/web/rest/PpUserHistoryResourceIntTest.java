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
import com.akujas.vcs.domain.PpUserHistory;
import com.akujas.vcs.domain.enumeration.TransactionStatus;
import com.akujas.vcs.domain.enumeration.TransactionType;
import com.akujas.vcs.repository.PpUserHistoryRepository;
import com.akujas.vcs.service.PpUserHistoryService;
import com.akujas.vcs.web.rest.PpUserHistoryResource;
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
 * Test class for the PpUserHistoryResource REST controller.
 *
 * @see PpUserHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VCSFrontEndApp.class)
public class PpUserHistoryResourceIntTest {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_LAST_UPDATED_PLAYPOINT = 1L;
    private static final Long UPDATED_LAST_UPDATED_PLAYPOINT = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final TransactionType DEFAULT_TRANSACTION_TYPE = TransactionType.CREDIT;
    private static final TransactionType UPDATED_TRANSACTION_TYPE = TransactionType.DEBIT;

    private static final Long DEFAULT_PRICE_POINT_VALUE = 1L;
    private static final Long UPDATED_PRICE_POINT_VALUE = 2L;

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final TransactionStatus DEFAULT_TRANSACTION_STATUS = TransactionStatus.SUCCESS;
    private static final TransactionStatus UPDATED_TRANSACTION_STATUS = TransactionStatus.FAIL;

    private static final Instant DEFAULT_TRANSACTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSACTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_USER_ACTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUC_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_PRODUC_DETAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_ID = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MID = "AAAAAAAAAA";
    private static final String UPDATED_MID = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_1 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_2 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_3 = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PARAMS = "AAAAAAAAAA";
    private static final String UPDATED_PARAMS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PpUserHistoryRepository ppUserHistoryRepository;

    

    @Autowired
    private PpUserHistoryService ppUserHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPpUserHistoryMockMvc;

    private PpUserHistory ppUserHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PpUserHistoryResource ppUserHistoryResource = new PpUserHistoryResource(ppUserHistoryService);
        this.restPpUserHistoryMockMvc = MockMvcBuilders.standaloneSetup(ppUserHistoryResource)
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
    public static PpUserHistory createEntity(EntityManager em) {
        PpUserHistory ppUserHistory = new PpUserHistory()
            .userId(DEFAULT_USER_ID)
            .lastUpdatedPlaypoint(DEFAULT_LAST_UPDATED_PLAYPOINT)
            .description(DEFAULT_DESCRIPTION)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .pricePointValue(DEFAULT_PRICE_POINT_VALUE)
            .source(DEFAULT_SOURCE)
            .transactionStatus(DEFAULT_TRANSACTION_STATUS)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .orderId(DEFAULT_ORDER_ID)
            .userAction(DEFAULT_USER_ACTION)
            .productId(DEFAULT_PRODUCT_ID)
            .productType(DEFAULT_PRODUCT_TYPE)
            .producDetail(DEFAULT_PRODUC_DETAIL)
            .walletId(DEFAULT_WALLET_ID)
            .mid(DEFAULT_MID)
            .param1(DEFAULT_PARAM_1)
            .param2(DEFAULT_PARAM_2)
            .param3(DEFAULT_PARAM_3)
            .params(DEFAULT_PARAMS)
            .created(DEFAULT_CREATED)
            .modifiedon(DEFAULT_MODIFIEDON);
        return ppUserHistory;
    }

    @Before
    public void initTest() {
        ppUserHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createPpUserHistory() throws Exception {
        int databaseSizeBeforeCreate = ppUserHistoryRepository.findAll().size();

        // Create the PpUserHistory
        restPpUserHistoryMockMvc.perform(post("/api/pp-user-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ppUserHistory)))
            .andExpect(status().isCreated());

        // Validate the PpUserHistory in the database
        List<PpUserHistory> ppUserHistoryList = ppUserHistoryRepository.findAll();
        assertThat(ppUserHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        PpUserHistory testPpUserHistory = ppUserHistoryList.get(ppUserHistoryList.size() - 1);
        assertThat(testPpUserHistory.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testPpUserHistory.getLastUpdatedPlaypoint()).isEqualTo(DEFAULT_LAST_UPDATED_PLAYPOINT);
        assertThat(testPpUserHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPpUserHistory.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testPpUserHistory.getPricePointValue()).isEqualTo(DEFAULT_PRICE_POINT_VALUE);
        assertThat(testPpUserHistory.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testPpUserHistory.getTransactionStatus()).isEqualTo(DEFAULT_TRANSACTION_STATUS);
        assertThat(testPpUserHistory.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testPpUserHistory.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testPpUserHistory.getUserAction()).isEqualTo(DEFAULT_USER_ACTION);
        assertThat(testPpUserHistory.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testPpUserHistory.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testPpUserHistory.getProducDetail()).isEqualTo(DEFAULT_PRODUC_DETAIL);
        assertThat(testPpUserHistory.getWalletId()).isEqualTo(DEFAULT_WALLET_ID);
        assertThat(testPpUserHistory.getMid()).isEqualTo(DEFAULT_MID);
        assertThat(testPpUserHistory.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testPpUserHistory.getParam2()).isEqualTo(DEFAULT_PARAM_2);
        assertThat(testPpUserHistory.getParam3()).isEqualTo(DEFAULT_PARAM_3);
        assertThat(testPpUserHistory.getParams()).isEqualTo(DEFAULT_PARAMS);
        assertThat(testPpUserHistory.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testPpUserHistory.getModifiedon()).isEqualTo(DEFAULT_MODIFIEDON);
    }

    @Test
    @Transactional
    public void createPpUserHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ppUserHistoryRepository.findAll().size();

        // Create the PpUserHistory with an existing ID
        ppUserHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPpUserHistoryMockMvc.perform(post("/api/pp-user-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ppUserHistory)))
            .andExpect(status().isBadRequest());

        // Validate the PpUserHistory in the database
        List<PpUserHistory> ppUserHistoryList = ppUserHistoryRepository.findAll();
        assertThat(ppUserHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPpUserHistories() throws Exception {
        // Initialize the database
        ppUserHistoryRepository.saveAndFlush(ppUserHistory);

        // Get all the ppUserHistoryList
        restPpUserHistoryMockMvc.perform(get("/api/pp-user-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ppUserHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedPlaypoint").value(hasItem(DEFAULT_LAST_UPDATED_PLAYPOINT.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].pricePointValue").value(hasItem(DEFAULT_PRICE_POINT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].transactionStatus").value(hasItem(DEFAULT_TRANSACTION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].userAction").value(hasItem(DEFAULT_USER_ACTION.toString())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].producDetail").value(hasItem(DEFAULT_PRODUC_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].walletId").value(hasItem(DEFAULT_WALLET_ID.toString())))
            .andExpect(jsonPath("$.[*].mid").value(hasItem(DEFAULT_MID.toString())))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1.toString())))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2.toString())))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3.toString())))
            .andExpect(jsonPath("$.[*].params").value(hasItem(DEFAULT_PARAMS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modifiedon").value(hasItem(DEFAULT_MODIFIEDON.toString())));
    }
    

    @Test
    @Transactional
    public void getPpUserHistory() throws Exception {
        // Initialize the database
        ppUserHistoryRepository.saveAndFlush(ppUserHistory);

        // Get the ppUserHistory
        restPpUserHistoryMockMvc.perform(get("/api/pp-user-histories/{id}", ppUserHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ppUserHistory.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.lastUpdatedPlaypoint").value(DEFAULT_LAST_UPDATED_PLAYPOINT.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()))
            .andExpect(jsonPath("$.pricePointValue").value(DEFAULT_PRICE_POINT_VALUE.intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.transactionStatus").value(DEFAULT_TRANSACTION_STATUS.toString()))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.userAction").value(DEFAULT_USER_ACTION.toString()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.producDetail").value(DEFAULT_PRODUC_DETAIL.toString()))
            .andExpect(jsonPath("$.walletId").value(DEFAULT_WALLET_ID.toString()))
            .andExpect(jsonPath("$.mid").value(DEFAULT_MID.toString()))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1.toString()))
            .andExpect(jsonPath("$.param2").value(DEFAULT_PARAM_2.toString()))
            .andExpect(jsonPath("$.param3").value(DEFAULT_PARAM_3.toString()))
            .andExpect(jsonPath("$.params").value(DEFAULT_PARAMS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modifiedon").value(DEFAULT_MODIFIEDON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPpUserHistory() throws Exception {
        // Get the ppUserHistory
        restPpUserHistoryMockMvc.perform(get("/api/pp-user-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePpUserHistory() throws Exception {
        // Initialize the database
        ppUserHistoryService.save(ppUserHistory);

        int databaseSizeBeforeUpdate = ppUserHistoryRepository.findAll().size();

        // Update the ppUserHistory
        PpUserHistory updatedPpUserHistory = ppUserHistoryRepository.findById(ppUserHistory.getId()).get();
        // Disconnect from session so that the updates on updatedPpUserHistory are not directly saved in db
        em.detach(updatedPpUserHistory);
        updatedPpUserHistory
            .userId(UPDATED_USER_ID)
            .lastUpdatedPlaypoint(UPDATED_LAST_UPDATED_PLAYPOINT)
            .description(UPDATED_DESCRIPTION)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .pricePointValue(UPDATED_PRICE_POINT_VALUE)
            .source(UPDATED_SOURCE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .orderId(UPDATED_ORDER_ID)
            .userAction(UPDATED_USER_ACTION)
            .productId(UPDATED_PRODUCT_ID)
            .productType(UPDATED_PRODUCT_TYPE)
            .producDetail(UPDATED_PRODUC_DETAIL)
            .walletId(UPDATED_WALLET_ID)
            .mid(UPDATED_MID)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .params(UPDATED_PARAMS)
            .created(UPDATED_CREATED)
            .modifiedon(UPDATED_MODIFIEDON);

        restPpUserHistoryMockMvc.perform(put("/api/pp-user-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPpUserHistory)))
            .andExpect(status().isOk());

        // Validate the PpUserHistory in the database
        List<PpUserHistory> ppUserHistoryList = ppUserHistoryRepository.findAll();
        assertThat(ppUserHistoryList).hasSize(databaseSizeBeforeUpdate);
        PpUserHistory testPpUserHistory = ppUserHistoryList.get(ppUserHistoryList.size() - 1);
        assertThat(testPpUserHistory.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPpUserHistory.getLastUpdatedPlaypoint()).isEqualTo(UPDATED_LAST_UPDATED_PLAYPOINT);
        assertThat(testPpUserHistory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPpUserHistory.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testPpUserHistory.getPricePointValue()).isEqualTo(UPDATED_PRICE_POINT_VALUE);
        assertThat(testPpUserHistory.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testPpUserHistory.getTransactionStatus()).isEqualTo(UPDATED_TRANSACTION_STATUS);
        assertThat(testPpUserHistory.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testPpUserHistory.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testPpUserHistory.getUserAction()).isEqualTo(UPDATED_USER_ACTION);
        assertThat(testPpUserHistory.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testPpUserHistory.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testPpUserHistory.getProducDetail()).isEqualTo(UPDATED_PRODUC_DETAIL);
        assertThat(testPpUserHistory.getWalletId()).isEqualTo(UPDATED_WALLET_ID);
        assertThat(testPpUserHistory.getMid()).isEqualTo(UPDATED_MID);
        assertThat(testPpUserHistory.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testPpUserHistory.getParam2()).isEqualTo(UPDATED_PARAM_2);
        assertThat(testPpUserHistory.getParam3()).isEqualTo(UPDATED_PARAM_3);
        assertThat(testPpUserHistory.getParams()).isEqualTo(UPDATED_PARAMS);
        assertThat(testPpUserHistory.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testPpUserHistory.getModifiedon()).isEqualTo(UPDATED_MODIFIEDON);
    }

    @Test
    @Transactional
    public void updateNonExistingPpUserHistory() throws Exception {
        int databaseSizeBeforeUpdate = ppUserHistoryRepository.findAll().size();

        // Create the PpUserHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPpUserHistoryMockMvc.perform(put("/api/pp-user-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ppUserHistory)))
            .andExpect(status().isBadRequest());

        // Validate the PpUserHistory in the database
        List<PpUserHistory> ppUserHistoryList = ppUserHistoryRepository.findAll();
        assertThat(ppUserHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePpUserHistory() throws Exception {
        // Initialize the database
        ppUserHistoryService.save(ppUserHistory);

        int databaseSizeBeforeDelete = ppUserHistoryRepository.findAll().size();

        // Get the ppUserHistory
        restPpUserHistoryMockMvc.perform(delete("/api/pp-user-histories/{id}", ppUserHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PpUserHistory> ppUserHistoryList = ppUserHistoryRepository.findAll();
        assertThat(ppUserHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PpUserHistory.class);
        PpUserHistory ppUserHistory1 = new PpUserHistory();
        ppUserHistory1.setId(1L);
        PpUserHistory ppUserHistory2 = new PpUserHistory();
        ppUserHistory2.setId(ppUserHistory1.getId());
        assertThat(ppUserHistory1).isEqualTo(ppUserHistory2);
        ppUserHistory2.setId(2L);
        assertThat(ppUserHistory1).isNotEqualTo(ppUserHistory2);
        ppUserHistory1.setId(null);
        assertThat(ppUserHistory1).isNotEqualTo(ppUserHistory2);
    }
}
