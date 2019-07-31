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
import com.akujas.vcs.domain.Wallet;
import com.akujas.vcs.domain.enumeration.AmountAlertThreshholdtype;
import com.akujas.vcs.domain.enumeration.WalletCategory;
import com.akujas.vcs.domain.enumeration.WalletSubcategory;
import com.akujas.vcs.domain.enumeration.WalletType;
import com.akujas.vcs.repository.WalletRepository;
import com.akujas.vcs.service.WalletService;
import com.akujas.vcs.web.rest.WalletResource;
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
 * Test class for the WalletResource REST controller.
 *
 * @see WalletResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VCSFrontEndApp.class)
public class WalletResourceIntTest {

    private static final String DEFAULT_MID = "AAAAAAAAAA";
    private static final String UPDATED_MID = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_ID = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_ID = "BBBBBBBBBB";

    private static final WalletCategory DEFAULT_WALLET_CATEGORY = WalletCategory.PROMOTION;
    private static final WalletCategory UPDATED_WALLET_CATEGORY = WalletCategory.LOYALITY;

    private static final WalletSubcategory DEFAULT_WALLET_SUBCATEGORY = WalletSubcategory.PROMOTION1;
    private static final WalletSubcategory UPDATED_WALLET_SUBCATEGORY = WalletSubcategory.PROMOTION2;

    private static final WalletType DEFAULT_WALLET_TYPE = WalletType.REVENUE;
    private static final WalletType UPDATED_WALLET_TYPE = WalletType.ESCROW;

    private static final Boolean DEFAULT_IS_SHOW = false;
    private static final Boolean UPDATED_IS_SHOW = true;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_APPROVED = false;
    private static final Boolean UPDATED_APPROVED = true;

    private static final Instant DEFAULT_APPROVED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_PRICE_POINT_VALUE = 1L;
    private static final Long UPDATED_PRICE_POINT_VALUE = 2L;

    private static final Long DEFAULT_TOTAL_PRICE_POINT_VALUE = 1L;
    private static final Long UPDATED_TOTAL_PRICE_POINT_VALUE = 2L;

    private static final Long DEFAULT_AMOUNT_ALERT_THRESHOLD = 1L;
    private static final Long UPDATED_AMOUNT_ALERT_THRESHOLD = 2L;

    private static final AmountAlertThreshholdtype DEFAULT_AMOUNT_ALERT_THRESHHOLDTYPE = AmountAlertThreshholdtype.FLAT;
    private static final AmountAlertThreshholdtype UPDATED_AMOUNT_ALERT_THRESHHOLDTYPE = AmountAlertThreshholdtype.PERCENTAGE;

    private static final Long DEFAULT_MAX_DEBIT_PER_REQUEST = 1L;
    private static final Long UPDATED_MAX_DEBIT_PER_REQUEST = 2L;

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
    private WalletRepository walletRepository;

    

    @Autowired
    private WalletService walletService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWalletMockMvc;

    private Wallet wallet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WalletResource walletResource = new WalletResource(walletService);
        this.restWalletMockMvc = MockMvcBuilders.standaloneSetup(walletResource)
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
    public static Wallet createEntity(EntityManager em) {
        Wallet wallet = new Wallet()
            .mid(DEFAULT_MID)
            .walletId(DEFAULT_WALLET_ID)
            .walletCategory(DEFAULT_WALLET_CATEGORY)
            .walletSubcategory(DEFAULT_WALLET_SUBCATEGORY)
            .walletType(DEFAULT_WALLET_TYPE)
            .isShow(DEFAULT_IS_SHOW)
            .isActive(DEFAULT_IS_ACTIVE)
            .approved(DEFAULT_APPROVED)
            .approvedOn(DEFAULT_APPROVED_ON)
            .pricePointValue(DEFAULT_PRICE_POINT_VALUE)
            .totalPricePointValue(DEFAULT_TOTAL_PRICE_POINT_VALUE)
            .amountAlertThreshold(DEFAULT_AMOUNT_ALERT_THRESHOLD)
            .amountAlertThreshholdtype(DEFAULT_AMOUNT_ALERT_THRESHHOLDTYPE)
            .maxDebitPerRequest(DEFAULT_MAX_DEBIT_PER_REQUEST)
            .param1(DEFAULT_PARAM_1)
            .param2(DEFAULT_PARAM_2)
            .param3(DEFAULT_PARAM_3)
            .params(DEFAULT_PARAMS)
            .created(DEFAULT_CREATED)
            .modifiedon(DEFAULT_MODIFIEDON);
        return wallet;
    }

    @Before
    public void initTest() {
        wallet = createEntity(em);
    }

    @Test
    @Transactional
    public void createWallet() throws Exception {
        int databaseSizeBeforeCreate = walletRepository.findAll().size();

        // Create the Wallet
        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isCreated());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeCreate + 1);
        Wallet testWallet = walletList.get(walletList.size() - 1);
        assertThat(testWallet.getMid()).isEqualTo(DEFAULT_MID);
        assertThat(testWallet.getWalletId()).isEqualTo(DEFAULT_WALLET_ID);
        assertThat(testWallet.getWalletCategory()).isEqualTo(DEFAULT_WALLET_CATEGORY);
        assertThat(testWallet.getWalletSubcategory()).isEqualTo(DEFAULT_WALLET_SUBCATEGORY);
        assertThat(testWallet.getWalletType()).isEqualTo(DEFAULT_WALLET_TYPE);
        assertThat(testWallet.isIsShow()).isEqualTo(DEFAULT_IS_SHOW);
        assertThat(testWallet.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testWallet.isApproved()).isEqualTo(DEFAULT_APPROVED);
        assertThat(testWallet.getApprovedOn()).isEqualTo(DEFAULT_APPROVED_ON);
        assertThat(testWallet.getPricePointValue()).isEqualTo(DEFAULT_PRICE_POINT_VALUE);
        assertThat(testWallet.getTotalPricePointValue()).isEqualTo(DEFAULT_TOTAL_PRICE_POINT_VALUE);
        assertThat(testWallet.getAmountAlertThreshold()).isEqualTo(DEFAULT_AMOUNT_ALERT_THRESHOLD);
        assertThat(testWallet.getAmountAlertThreshholdtype()).isEqualTo(DEFAULT_AMOUNT_ALERT_THRESHHOLDTYPE);
        assertThat(testWallet.getMaxDebitPerRequest()).isEqualTo(DEFAULT_MAX_DEBIT_PER_REQUEST);
        assertThat(testWallet.getParam1()).isEqualTo(DEFAULT_PARAM_1);
        assertThat(testWallet.getParam2()).isEqualTo(DEFAULT_PARAM_2);
        assertThat(testWallet.getParam3()).isEqualTo(DEFAULT_PARAM_3);
        assertThat(testWallet.getParams()).isEqualTo(DEFAULT_PARAMS);
        assertThat(testWallet.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testWallet.getModifiedon()).isEqualTo(DEFAULT_MODIFIEDON);
    }

    @Test
    @Transactional
    public void createWalletWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletRepository.findAll().size();

        // Create the Wallet with an existing ID
        wallet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWallets() throws Exception {
        // Initialize the database
        walletRepository.saveAndFlush(wallet);

        // Get all the walletList
        restWalletMockMvc.perform(get("/api/wallets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wallet.getId().intValue())))
            .andExpect(jsonPath("$.[*].mid").value(hasItem(DEFAULT_MID.toString())))
            .andExpect(jsonPath("$.[*].walletId").value(hasItem(DEFAULT_WALLET_ID.toString())))
            .andExpect(jsonPath("$.[*].walletCategory").value(hasItem(DEFAULT_WALLET_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].walletSubcategory").value(hasItem(DEFAULT_WALLET_SUBCATEGORY.toString())))
            .andExpect(jsonPath("$.[*].walletType").value(hasItem(DEFAULT_WALLET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isShow").value(hasItem(DEFAULT_IS_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].approvedOn").value(hasItem(DEFAULT_APPROVED_ON.toString())))
            .andExpect(jsonPath("$.[*].pricePointValue").value(hasItem(DEFAULT_PRICE_POINT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].totalPricePointValue").value(hasItem(DEFAULT_TOTAL_PRICE_POINT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].amountAlertThreshold").value(hasItem(DEFAULT_AMOUNT_ALERT_THRESHOLD.intValue())))
            .andExpect(jsonPath("$.[*].amountAlertThreshholdtype").value(hasItem(DEFAULT_AMOUNT_ALERT_THRESHHOLDTYPE.toString())))
            .andExpect(jsonPath("$.[*].maxDebitPerRequest").value(hasItem(DEFAULT_MAX_DEBIT_PER_REQUEST.intValue())))
            .andExpect(jsonPath("$.[*].param1").value(hasItem(DEFAULT_PARAM_1.toString())))
            .andExpect(jsonPath("$.[*].param2").value(hasItem(DEFAULT_PARAM_2.toString())))
            .andExpect(jsonPath("$.[*].param3").value(hasItem(DEFAULT_PARAM_3.toString())))
            .andExpect(jsonPath("$.[*].params").value(hasItem(DEFAULT_PARAMS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modifiedon").value(hasItem(DEFAULT_MODIFIEDON.toString())));
    }
    

    @Test
    @Transactional
    public void getWallet() throws Exception {
        // Initialize the database
        walletRepository.saveAndFlush(wallet);

        // Get the wallet
        restWalletMockMvc.perform(get("/api/wallets/{id}", wallet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wallet.getId().intValue()))
            .andExpect(jsonPath("$.mid").value(DEFAULT_MID.toString()))
            .andExpect(jsonPath("$.walletId").value(DEFAULT_WALLET_ID.toString()))
            .andExpect(jsonPath("$.walletCategory").value(DEFAULT_WALLET_CATEGORY.toString()))
            .andExpect(jsonPath("$.walletSubcategory").value(DEFAULT_WALLET_SUBCATEGORY.toString()))
            .andExpect(jsonPath("$.walletType").value(DEFAULT_WALLET_TYPE.toString()))
            .andExpect(jsonPath("$.isShow").value(DEFAULT_IS_SHOW.booleanValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED.booleanValue()))
            .andExpect(jsonPath("$.approvedOn").value(DEFAULT_APPROVED_ON.toString()))
            .andExpect(jsonPath("$.pricePointValue").value(DEFAULT_PRICE_POINT_VALUE.intValue()))
            .andExpect(jsonPath("$.totalPricePointValue").value(DEFAULT_TOTAL_PRICE_POINT_VALUE.intValue()))
            .andExpect(jsonPath("$.amountAlertThreshold").value(DEFAULT_AMOUNT_ALERT_THRESHOLD.intValue()))
            .andExpect(jsonPath("$.amountAlertThreshholdtype").value(DEFAULT_AMOUNT_ALERT_THRESHHOLDTYPE.toString()))
            .andExpect(jsonPath("$.maxDebitPerRequest").value(DEFAULT_MAX_DEBIT_PER_REQUEST.intValue()))
            .andExpect(jsonPath("$.param1").value(DEFAULT_PARAM_1.toString()))
            .andExpect(jsonPath("$.param2").value(DEFAULT_PARAM_2.toString()))
            .andExpect(jsonPath("$.param3").value(DEFAULT_PARAM_3.toString()))
            .andExpect(jsonPath("$.params").value(DEFAULT_PARAMS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modifiedon").value(DEFAULT_MODIFIEDON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingWallet() throws Exception {
        // Get the wallet
        restWalletMockMvc.perform(get("/api/wallets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWallet() throws Exception {
        // Initialize the database
        walletService.save(wallet);

        int databaseSizeBeforeUpdate = walletRepository.findAll().size();

        // Update the wallet
        Wallet updatedWallet = walletRepository.findById(wallet.getId()).get();
        // Disconnect from session so that the updates on updatedWallet are not directly saved in db
        em.detach(updatedWallet);
        updatedWallet
            .mid(UPDATED_MID)
            .walletId(UPDATED_WALLET_ID)
            .walletCategory(UPDATED_WALLET_CATEGORY)
            .walletSubcategory(UPDATED_WALLET_SUBCATEGORY)
            .walletType(UPDATED_WALLET_TYPE)
            .isShow(UPDATED_IS_SHOW)
            .isActive(UPDATED_IS_ACTIVE)
            .approved(UPDATED_APPROVED)
            .approvedOn(UPDATED_APPROVED_ON)
            .pricePointValue(UPDATED_PRICE_POINT_VALUE)
            .totalPricePointValue(UPDATED_TOTAL_PRICE_POINT_VALUE)
            .amountAlertThreshold(UPDATED_AMOUNT_ALERT_THRESHOLD)
            .amountAlertThreshholdtype(UPDATED_AMOUNT_ALERT_THRESHHOLDTYPE)
            .maxDebitPerRequest(UPDATED_MAX_DEBIT_PER_REQUEST)
            .param1(UPDATED_PARAM_1)
            .param2(UPDATED_PARAM_2)
            .param3(UPDATED_PARAM_3)
            .params(UPDATED_PARAMS)
            .created(UPDATED_CREATED)
            .modifiedon(UPDATED_MODIFIEDON);

        restWalletMockMvc.perform(put("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWallet)))
            .andExpect(status().isOk());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeUpdate);
        Wallet testWallet = walletList.get(walletList.size() - 1);
        assertThat(testWallet.getMid()).isEqualTo(UPDATED_MID);
        assertThat(testWallet.getWalletId()).isEqualTo(UPDATED_WALLET_ID);
        assertThat(testWallet.getWalletCategory()).isEqualTo(UPDATED_WALLET_CATEGORY);
        assertThat(testWallet.getWalletSubcategory()).isEqualTo(UPDATED_WALLET_SUBCATEGORY);
        assertThat(testWallet.getWalletType()).isEqualTo(UPDATED_WALLET_TYPE);
        assertThat(testWallet.isIsShow()).isEqualTo(UPDATED_IS_SHOW);
        assertThat(testWallet.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testWallet.isApproved()).isEqualTo(UPDATED_APPROVED);
        assertThat(testWallet.getApprovedOn()).isEqualTo(UPDATED_APPROVED_ON);
        assertThat(testWallet.getPricePointValue()).isEqualTo(UPDATED_PRICE_POINT_VALUE);
        assertThat(testWallet.getTotalPricePointValue()).isEqualTo(UPDATED_TOTAL_PRICE_POINT_VALUE);
        assertThat(testWallet.getAmountAlertThreshold()).isEqualTo(UPDATED_AMOUNT_ALERT_THRESHOLD);
        assertThat(testWallet.getAmountAlertThreshholdtype()).isEqualTo(UPDATED_AMOUNT_ALERT_THRESHHOLDTYPE);
        assertThat(testWallet.getMaxDebitPerRequest()).isEqualTo(UPDATED_MAX_DEBIT_PER_REQUEST);
        assertThat(testWallet.getParam1()).isEqualTo(UPDATED_PARAM_1);
        assertThat(testWallet.getParam2()).isEqualTo(UPDATED_PARAM_2);
        assertThat(testWallet.getParam3()).isEqualTo(UPDATED_PARAM_3);
        assertThat(testWallet.getParams()).isEqualTo(UPDATED_PARAMS);
        assertThat(testWallet.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testWallet.getModifiedon()).isEqualTo(UPDATED_MODIFIEDON);
    }

    @Test
    @Transactional
    public void updateNonExistingWallet() throws Exception {
        int databaseSizeBeforeUpdate = walletRepository.findAll().size();

        // Create the Wallet

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWalletMockMvc.perform(put("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWallet() throws Exception {
        // Initialize the database
        walletService.save(wallet);

        int databaseSizeBeforeDelete = walletRepository.findAll().size();

        // Get the wallet
        restWalletMockMvc.perform(delete("/api/wallets/{id}", wallet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wallet.class);
        Wallet wallet1 = new Wallet();
        wallet1.setId(1L);
        Wallet wallet2 = new Wallet();
        wallet2.setId(wallet1.getId());
        assertThat(wallet1).isEqualTo(wallet2);
        wallet2.setId(2L);
        assertThat(wallet1).isNotEqualTo(wallet2);
        wallet1.setId(null);
        assertThat(wallet1).isNotEqualTo(wallet2);
    }
}
