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
import com.akujas.vcs.domain.WalletHistory;
import com.akujas.vcs.domain.enumeration.TransactionStatus;
import com.akujas.vcs.domain.enumeration.TransactionType;
import com.akujas.vcs.repository.WalletHistoryRepository;
import com.akujas.vcs.service.WalletHistoryService;
import com.akujas.vcs.web.rest.WalletHistoryResource;
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
 * Test class for the WalletHistoryResource REST controller.
 *
 * @see WalletHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VCSFrontEndApp.class)
public class WalletHistoryResourceIntTest {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_LAST_UPDATED_PLAYPOINT = 1L;
    private static final Long UPDATED_LAST_UPDATED_PLAYPOINT = 2L;

    private static final Long DEFAULT_PRICE_POINT_VALUE = 1L;
    private static final Long UPDATED_PRICE_POINT_VALUE = 2L;

    private static final TransactionType DEFAULT_TRANSACTION_TYPE = TransactionType.CREDIT;
    private static final TransactionType UPDATED_TRANSACTION_TYPE = TransactionType.DEBIT;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_USER_ACTION = "BBBBBBBBBB";

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

    private static final String DEFAULT_PARAMS = "AAAAAAAAAA";
    private static final String UPDATED_PARAMS = "BBBBBBBBBB";

    private static final TransactionStatus DEFAULT_TRANSACTION_STATUS = TransactionStatus.SUCCESS;
    private static final TransactionStatus UPDATED_TRANSACTION_STATUS = TransactionStatus.FAIL;

    private static final Instant DEFAULT_TRANSACTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSACTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_WALLET_ID = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MID = "AAAAAAAAAA";
    private static final String UPDATED_MID = "BBBBBBBBBB";

    @Autowired
    private WalletHistoryRepository walletHistoryRepository;

    

    @Autowired
    private WalletHistoryService walletHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWalletHistoryMockMvc;

    private WalletHistory walletHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WalletHistoryResource walletHistoryResource = new WalletHistoryResource(walletHistoryService);
        this.restWalletHistoryMockMvc = MockMvcBuilders.standaloneSetup(walletHistoryResource)
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
    public static WalletHistory createEntity(EntityManager em) {
        WalletHistory walletHistory = new WalletHistory()
            .orderId(DEFAULT_ORDER_ID)
            .userId(DEFAULT_USER_ID)
            .lastUpdatedPlaypoint(DEFAULT_LAST_UPDATED_PLAYPOINT)
            .pricePointValue(DEFAULT_PRICE_POINT_VALUE)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .source(DEFAULT_SOURCE)
            .userAction(DEFAULT_USER_ACTION)
            .param1(DEFAULT_PARAM_1)
            .param2(DEFAULT_PARAM_2)
            .param3(DEFAULT_PARAM_3)
            .created(DEFAULT_CREATED)
            .modifiedon(DEFAULT_MODIFIEDON)
            .params(DEFAULT_PARAMS)
            .transactionStatus(DEFAULT_TRANSACTION_STATUS)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .walletId(DEFAULT_WALLET_ID)
            .mid(DEFAULT_MID);
        return walletHistory;
    }

    @Before
    public void initTest() {
        walletHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createWalletHistory() throws Exception {
        int databaseSizeBeforeCreate = walletHistoryRepository.findAll().size();

        // Create the WalletHistory
        restWalletHistoryMockMvc.perform(post("/api/wallet-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletHistory)))
            .andExpect(status().isCreated());

        // Validate the WalletHistory in the database
        List<WalletHistory> walletHistoryList = walletHistoryRepository.findAll();
        assertThat(walletHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        WalletHistory testWalletHistory = walletHistoryList.get(walletHistoryList.size() - 1);
        assertThat(testWalletHistory.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testWalletHistory.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testWalletHistory.getLastUpdatedPlaypoint()).isEqualTo(DEFAULT_LAST_UPDATED_PLAYPOINT);
        assertThat(testWalletHistory.getPricePointValue()).isEqualTo(DEFAULT_PRICE_POINT_VALUE);
        assertThat(testWalletHistory.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testWalletHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testWalletHistory.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testWalletHistory.getUserAction()).isEqualTo(DEFAULT_USER_ACTION);
        assertThat(testWalletHistory.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testWalletHistory.getParam2()).isEqualTo(DEFAULT_PARAM_2);
        assertThat(testWalletHistory.getParam3()).isEqualTo(DEFAULT_PARAM_3);
        assertThat(testWalletHistory.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testWalletHistory.getModifiedon()).isEqualTo(DEFAULT_MODIFIEDON);
        assertThat(testWalletHistory.getParams()).isEqualTo(DEFAULT_PARAMS);
        assertThat(testWalletHistory.getTransactionStatus()).isEqualTo(DEFAULT_TRANSACTION_STATUS);
        assertThat(testWalletHistory.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testWalletHistory.getWalletId()).isEqualTo(DEFAULT_WALLET_ID);
        assertThat(testWalletHistory.getMid()).isEqualTo(DEFAULT_MID);
    }

    @Test
    @Transactional
    public void createWalletHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletHistoryRepository.findAll().size();

        // Create the WalletHistory with an existing ID
        walletHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletHistoryMockMvc.perform(post("/api/wallet-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletHistory)))
            .andExpect(status().isBadRequest());

        // Validate the WalletHistory in the database
        List<WalletHistory> walletHistoryList = walletHistoryRepository.findAll();
        assertThat(walletHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWalletHistories() throws Exception {
        // Initialize the database
        walletHistoryRepository.saveAndFlush(walletHistory);

        // Get all the walletHistoryList
        restWalletHistoryMockMvc.perform(get("/api/wallet-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(walletHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedPlaypoint").value(hasItem(DEFAULT_LAST_UPDATED_PLAYPOINT.intValue())))
            .andExpect(jsonPath("$.[*].pricePointValue").value(hasItem(DEFAULT_PRICE_POINT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].userAction").value(hasItem(DEFAULT_USER_ACTION.toString())))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1.toString())))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2.toString())))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modifiedon").value(hasItem(DEFAULT_MODIFIEDON.toString())))
            .andExpect(jsonPath("$.[*].params").value(hasItem(DEFAULT_PARAMS.toString())))
            .andExpect(jsonPath("$.[*].transactionStatus").value(hasItem(DEFAULT_TRANSACTION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].transactionDate").value(hasItem(DEFAULT_TRANSACTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].walletId").value(hasItem(DEFAULT_WALLET_ID.toString())))
            .andExpect(jsonPath("$.[*].mid").value(hasItem(DEFAULT_MID.toString())));
    }
    

    @Test
    @Transactional
    public void getWalletHistory() throws Exception {
        // Initialize the database
        walletHistoryRepository.saveAndFlush(walletHistory);

        // Get the walletHistory
        restWalletHistoryMockMvc.perform(get("/api/wallet-histories/{id}", walletHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(walletHistory.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.lastUpdatedPlaypoint").value(DEFAULT_LAST_UPDATED_PLAYPOINT.intValue()))
            .andExpect(jsonPath("$.pricePointValue").value(DEFAULT_PRICE_POINT_VALUE.intValue()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.userAction").value(DEFAULT_USER_ACTION.toString()))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1.toString()))
            .andExpect(jsonPath("$.param2").value(DEFAULT_PARAM_2.toString()))
            .andExpect(jsonPath("$.param3").value(DEFAULT_PARAM_3.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modifiedon").value(DEFAULT_MODIFIEDON.toString()))
            .andExpect(jsonPath("$.params").value(DEFAULT_PARAMS.toString()))
            .andExpect(jsonPath("$.transactionStatus").value(DEFAULT_TRANSACTION_STATUS.toString()))
            .andExpect(jsonPath("$.transactionDate").value(DEFAULT_TRANSACTION_DATE.toString()))
            .andExpect(jsonPath("$.walletId").value(DEFAULT_WALLET_ID.toString()))
            .andExpect(jsonPath("$.mid").value(DEFAULT_MID.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingWalletHistory() throws Exception {
        // Get the walletHistory
        restWalletHistoryMockMvc.perform(get("/api/wallet-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWalletHistory() throws Exception {
        // Initialize the database
        walletHistoryService.save(walletHistory);

        int databaseSizeBeforeUpdate = walletHistoryRepository.findAll().size();

        // Update the walletHistory
        WalletHistory updatedWalletHistory = walletHistoryRepository.findById(walletHistory.getId()).get();
        // Disconnect from session so that the updates on updatedWalletHistory are not directly saved in db
        em.detach(updatedWalletHistory);
        updatedWalletHistory
            .orderId(UPDATED_ORDER_ID)
            .userId(UPDATED_USER_ID)
            .lastUpdatedPlaypoint(UPDATED_LAST_UPDATED_PLAYPOINT)
            .pricePointValue(UPDATED_PRICE_POINT_VALUE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .description(UPDATED_DESCRIPTION)
            .source(UPDATED_SOURCE)
            .userAction(UPDATED_USER_ACTION)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .created(UPDATED_CREATED)
            .modifiedon(UPDATED_MODIFIEDON)
            .params(UPDATED_PARAMS)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .walletId(UPDATED_WALLET_ID)
            .mid(UPDATED_MID);

        restWalletHistoryMockMvc.perform(put("/api/wallet-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWalletHistory)))
            .andExpect(status().isOk());

        // Validate the WalletHistory in the database
        List<WalletHistory> walletHistoryList = walletHistoryRepository.findAll();
        assertThat(walletHistoryList).hasSize(databaseSizeBeforeUpdate);
        WalletHistory testWalletHistory = walletHistoryList.get(walletHistoryList.size() - 1);
        assertThat(testWalletHistory.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testWalletHistory.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testWalletHistory.getLastUpdatedPlaypoint()).isEqualTo(UPDATED_LAST_UPDATED_PLAYPOINT);
        assertThat(testWalletHistory.getPricePointValue()).isEqualTo(UPDATED_PRICE_POINT_VALUE);
        assertThat(testWalletHistory.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testWalletHistory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testWalletHistory.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testWalletHistory.getUserAction()).isEqualTo(UPDATED_USER_ACTION);
        assertThat(testWalletHistory.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testWalletHistory.getParam2()).isEqualTo(UPDATED_PARAM_2);
        assertThat(testWalletHistory.getParam3()).isEqualTo(UPDATED_PARAM_3);
        assertThat(testWalletHistory.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testWalletHistory.getModifiedon()).isEqualTo(UPDATED_MODIFIEDON);
        assertThat(testWalletHistory.getParams()).isEqualTo(UPDATED_PARAMS);
        assertThat(testWalletHistory.getTransactionStatus()).isEqualTo(UPDATED_TRANSACTION_STATUS);
        assertThat(testWalletHistory.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testWalletHistory.getWalletId()).isEqualTo(UPDATED_WALLET_ID);
        assertThat(testWalletHistory.getMid()).isEqualTo(UPDATED_MID);
    }

    @Test
    @Transactional
    public void updateNonExistingWalletHistory() throws Exception {
        int databaseSizeBeforeUpdate = walletHistoryRepository.findAll().size();

        // Create the WalletHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWalletHistoryMockMvc.perform(put("/api/wallet-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletHistory)))
            .andExpect(status().isBadRequest());

        // Validate the WalletHistory in the database
        List<WalletHistory> walletHistoryList = walletHistoryRepository.findAll();
        assertThat(walletHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWalletHistory() throws Exception {
        // Initialize the database
        walletHistoryService.save(walletHistory);

        int databaseSizeBeforeDelete = walletHistoryRepository.findAll().size();

        // Get the walletHistory
        restWalletHistoryMockMvc.perform(delete("/api/wallet-histories/{id}", walletHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WalletHistory> walletHistoryList = walletHistoryRepository.findAll();
        assertThat(walletHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletHistory.class);
        WalletHistory walletHistory1 = new WalletHistory();
        walletHistory1.setId(1L);
        WalletHistory walletHistory2 = new WalletHistory();
        walletHistory2.setId(walletHistory1.getId());
        assertThat(walletHistory1).isEqualTo(walletHistory2);
        walletHistory2.setId(2L);
        assertThat(walletHistory1).isNotEqualTo(walletHistory2);
        walletHistory1.setId(null);
        assertThat(walletHistory1).isNotEqualTo(walletHistory2);
    }
}
