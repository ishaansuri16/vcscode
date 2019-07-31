package com.akujas.vcs.domain;


import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.akujas.vcs.domain.enumeration.AmountAlertThreshholdtype;
import com.akujas.vcs.domain.enumeration.WalletCategory;
import com.akujas.vcs.domain.enumeration.WalletSubcategory;
import com.akujas.vcs.domain.enumeration.WalletSubcategoryConverter;
import com.akujas.vcs.domain.enumeration.WalletType;

/**
 * A Wallet.
 */
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mid")
    private String mid;

    @Column(name = "wallet_id")
    private String walletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_category")
    private WalletCategory walletCategory;

    @Column(name = "wallet_subcategory")
    @Convert(converter = WalletSubcategoryConverter.class)
    private WalletSubcategory walletSubcategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_type")
    private WalletType walletType;

    @Column(name = "isshow")
    private Boolean isShow;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "approved_on")
    private Instant approvedOn;

    @Column(name = "price_point_value")
    private Long pricePointValue;

    @Column(name = "total_price_point_value")
    private Long totalPricePointValue;

    @Column(name = "amount_alert_threshold")
    private Long amountAlertThreshold;

    @Enumerated(EnumType.STRING)
    @Column(name = "amount_alert_threshhold_type")
    private AmountAlertThreshholdtype amountAlertThreshholdtype;

    @Column(name = "max_debit_per_request")
    private Long maxDebitPerRequest;

    @Column(name = "param1")
    private String param1;

    @Column(name = "param2")
    private String param2;

    @Column(name = "param3")
    private String param3;

    @Column(name = "params")
    private String params;

    @Column(name = "created")
    private Instant created;

    @Column(name = "modifiedon")
    private Instant modifiedon;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public Wallet mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getWalletId() {
        return walletId;
    }

    public Wallet walletId(String walletId) {
        this.walletId = walletId;
        return this;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public WalletCategory getWalletCategory() {
        return walletCategory;
    }

    public Wallet walletCategory(WalletCategory walletCategory) {
        this.walletCategory = walletCategory;
        return this;
    }

    public void setWalletCategory(WalletCategory walletCategory) {
        this.walletCategory = walletCategory;
    }

    public WalletSubcategory getWalletSubcategory() {
        return walletSubcategory;
    }

    public Wallet walletSubcategory(WalletSubcategory walletSubcategory) {
        this.walletSubcategory = walletSubcategory;
        return this;
    }

    public void setWalletSubcategory(WalletSubcategory walletSubcategory) {
        this.walletSubcategory = walletSubcategory;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public Wallet walletType(WalletType walletType) {
        this.walletType = walletType;
        return this;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public Boolean isIsShow() {
        return isShow;
    }

    public Wallet isShow(Boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Wallet isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isApproved() {
        return approved;
    }

    public Wallet approved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Instant getApprovedOn() {
        return approvedOn;
    }

    public Wallet approvedOn(Instant approvedOn) {
        this.approvedOn = approvedOn;
        return this;
    }

    public void setApprovedOn(Instant approvedOn) {
        this.approvedOn = approvedOn;
    }

    public Long getPricePointValue() {
        return pricePointValue;
    }

    public Wallet pricePointValue(Long pricePointValue) {
        this.pricePointValue = pricePointValue;
        return this;
    }

    public void setPricePointValue(Long pricePointValue) {
        this.pricePointValue = pricePointValue;
    }

    public Long getTotalPricePointValue() {
        return totalPricePointValue;
    }

    public Wallet totalPricePointValue(Long totalPricePointValue) {
        this.totalPricePointValue = totalPricePointValue;
        return this;
    }

    public void setTotalPricePointValue(Long totalPricePointValue) {
        this.totalPricePointValue = totalPricePointValue;
    }

    public Long getAmountAlertThreshold() {
        return amountAlertThreshold;
    }

    public Wallet amountAlertThreshold(Long amountAlertThreshold) {
        this.amountAlertThreshold = amountAlertThreshold;
        return this;
    }

    public void setAmountAlertThreshold(Long amountAlertThreshold) {
        this.amountAlertThreshold = amountAlertThreshold;
    }

    public AmountAlertThreshholdtype getAmountAlertThreshholdtype() {
        return amountAlertThreshholdtype;
    }

    public Wallet amountAlertThreshholdtype(AmountAlertThreshholdtype amountAlertThreshholdtype) {
        this.amountAlertThreshholdtype = amountAlertThreshholdtype;
        return this;
    }

    public void setAmountAlertThreshholdtype(AmountAlertThreshholdtype amountAlertThreshholdtype) {
        this.amountAlertThreshholdtype = amountAlertThreshholdtype;
    }

    public Long getMaxDebitPerRequest() {
        return maxDebitPerRequest;
    }

    public Wallet maxDebitPerRequest(Long maxDebitPerRequest) {
        this.maxDebitPerRequest = maxDebitPerRequest;
        return this;
    }

    public void setMaxDebitPerRequest(Long maxDebitPerRequest) {
        this.maxDebitPerRequest = maxDebitPerRequest;
    }

    public String getParam1() {
        return param1;
    }

    public Wallet param1(String param1) {
        this.param1 = param1;
        return this;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public Wallet param2(String param2) {
        this.param2 = param2;
        return this;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public Wallet param3(String param3) {
        this.param3 = param3;
        return this;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParams() {
        return params;
    }

    public Wallet params(String params) {
        this.params = params;
        return this;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Instant getCreated() {
        return created;
    }

    public Wallet created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModifiedon() {
        return modifiedon;
    }

    public Wallet modifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
        return this;
    }

    public void setModifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wallet wallet = (Wallet) o;
        if (wallet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wallet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", mid='" + getMid() + "'" +
            ", walletId='" + getWalletId() + "'" +
            ", walletCategory='" + getWalletCategory() + "'" +
            ", walletSubcategory='" + getWalletSubcategory() + "'" +
            ", walletType='" + getWalletType() + "'" +
            ", isShow='" + isIsShow() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", approved='" + isApproved() + "'" +
            ", approvedOn='" + getApprovedOn() + "'" +
            ", pricePointValue=" + getPricePointValue() +
            ", totalPricePointValue=" + getTotalPricePointValue() +
            ", amountAlertThreshold=" + getAmountAlertThreshold() +
            ", amountAlertThreshholdtype='" + getAmountAlertThreshholdtype() + "'" +
            ", maxDebitPerRequest=" + getMaxDebitPerRequest() +
            ", param1='" + getParam1() + "'" +
            ", param2='" + getParam2() + "'" +
            ", param3='" + getParam3() + "'" +
            ", params='" + getParams() + "'" +
            ", created='" + getCreated() + "'" +
            ", modifiedon='" + getModifiedon() + "'" +
            "}";
    }
}
