package com.akujas.vcs.domain;


import javax.persistence.*;

import com.akujas.vcs.domain.enumeration.TransactionStatus;
import com.akujas.vcs.domain.enumeration.TransactionType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PpUserHistory.
 */
@Entity
@Table(name = "pp_user_history")
public class PpUserHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "last_updated_playpoint")
    private Long lastUpdatedPlaypoint;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "price_point_value")
    private Long pricePointValue;

    @Column(name = "source")
    private String source;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_action")
    private String userAction;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_detail")
    private String producDetail;

    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "mid")
    private String mid;

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

    public String getUserId() {
        return userId;
    }

    public PpUserHistory userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLastUpdatedPlaypoint() {
        return lastUpdatedPlaypoint;
    }

    public PpUserHistory lastUpdatedPlaypoint(Long lastUpdatedPlaypoint) {
        this.lastUpdatedPlaypoint = lastUpdatedPlaypoint;
        return this;
    }

    public void setLastUpdatedPlaypoint(Long lastUpdatedPlaypoint) {
        this.lastUpdatedPlaypoint = lastUpdatedPlaypoint;
    }

    public String getDescription() {
        return description;
    }

    public PpUserHistory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public PpUserHistory transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getPricePointValue() {
        return pricePointValue;
    }

    public PpUserHistory pricePointValue(Long pricePointValue) {
        this.pricePointValue = pricePointValue;
        return this;
    }

    public void setPricePointValue(Long pricePointValue) {
        this.pricePointValue = pricePointValue;
    }

    public String getSource() {
        return source;
    }

    public PpUserHistory source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public PpUserHistory transactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public PpUserHistory transactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public PpUserHistory orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserAction() {
        return userAction;
    }

    public PpUserHistory userAction(String userAction) {
        this.userAction = userAction;
        return this;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getProductId() {
        return productId;
    }

    public PpUserHistory productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public PpUserHistory productType(String productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProducDetail() {
        return producDetail;
    }

    public PpUserHistory producDetail(String producDetail) {
        this.producDetail = producDetail;
        return this;
    }

    public void setProducDetail(String producDetail) {
        this.producDetail = producDetail;
    }

    public String getWalletId() {
        return walletId;
    }

    public PpUserHistory walletId(String walletId) {
        this.walletId = walletId;
        return this;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getMid() {
        return mid;
    }

    public PpUserHistory mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getParam1() {
        return param1;
    }

    public PpUserHistory param1(String param1) {
        this.param1 = param1;
        return this;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public PpUserHistory param2(String param2) {
        this.param2 = param2;
        return this;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public PpUserHistory param3(String param3) {
        this.param3 = param3;
        return this;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParams() {
        return params;
    }

    public PpUserHistory params(String params) {
        this.params = params;
        return this;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Instant getCreated() {
        return created;
    }

    public PpUserHistory created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModifiedon() {
        return modifiedon;
    }

    public PpUserHistory modifiedon(Instant modifiedon) {
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
        PpUserHistory ppUserHistory = (PpUserHistory) o;
        if (ppUserHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ppUserHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PpUserHistory{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", lastUpdatedPlaypoint=" + getLastUpdatedPlaypoint() +
            ", description='" + getDescription() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", pricePointValue=" + getPricePointValue() +
            ", source='" + getSource() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", userAction='" + getUserAction() + "'" +
            ", productId='" + getProductId() + "'" +
            ", productType='" + getProductType() + "'" +
            ", producDetail='" + getProducDetail() + "'" +
            ", walletId='" + getWalletId() + "'" +
            ", mid='" + getMid() + "'" +
            ", param1='" + getParam1() + "'" +
            ", param2='" + getParam2() + "'" +
            ", param3='" + getParam3() + "'" +
            ", params='" + getParams() + "'" +
            ", created='" + getCreated() + "'" +
            ", modifiedon='" + getModifiedon() + "'" +
            "}";
    }
}
