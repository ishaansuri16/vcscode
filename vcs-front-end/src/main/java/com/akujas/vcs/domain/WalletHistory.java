package com.akujas.vcs.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import com.akujas.vcs.domain.enumeration.TransactionStatus;
import com.akujas.vcs.domain.enumeration.TransactionType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Task entity.
 * @author The JHipster team.
 */
@ApiModel(description = "Task entity. @author The JHipster team.")
@Entity
@Table(name = "wallet_history")
public class WalletHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "last_updated_playpoint")
    private Long lastUpdatedPlaypoint;

    @Column(name = "price_point_value")
    private Long pricePointValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    @Column(name = "source")
    private String source;

    @Column(name = "user_action")
    private String userAction;

    @Column(name = "param1")
    private String param1;

    @Column(name = "param2")
    private String param2;

    @Column(name = "param3")
    private String param3;

    @Column(name = "created")
    private Instant created;

    @Column(name = "modifiedon")
    private Instant modifiedon;

    @Column(name = "params")
    private String params;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "mid")
    private String mid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public WalletHistory orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public WalletHistory userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLastUpdatedPlaypoint() {
        return lastUpdatedPlaypoint;
    }

    public WalletHistory lastUpdatedPlaypoint(Long lastUpdatedPlaypoint) {
        this.lastUpdatedPlaypoint = lastUpdatedPlaypoint;
        return this;
    }

    public void setLastUpdatedPlaypoint(Long lastUpdatedPlaypoint) {
        this.lastUpdatedPlaypoint = lastUpdatedPlaypoint;
    }

    public Long getPricePointValue() {
        return pricePointValue;
    }

    public WalletHistory pricePointValue(Long pricePointValue) {
        this.pricePointValue = pricePointValue;
        return this;
    }

    public void setPricePointValue(Long pricePointValue) {
        this.pricePointValue = pricePointValue;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public WalletHistory transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public WalletHistory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public WalletHistory source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserAction() {
        return userAction;
    }

    public WalletHistory userAction(String userAction) {
        this.userAction = userAction;
        return this;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getParam1() {
        return param1;
    }

    public WalletHistory param1(String param1) {
        this.param1 = param1;
        return this;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public WalletHistory param2(String param2) {
        this.param2 = param2;
        return this;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public WalletHistory param3(String param3) {
        this.param3 = param3;
        return this;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public Instant getCreated() {
        return created;
    }

    public WalletHistory created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModifiedon() {
        return modifiedon;
    }

    public WalletHistory modifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
        return this;
    }

    public void setModifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
    }

    public String getParams() {
        return params;
    }

    public WalletHistory params(String params) {
        this.params = params;
        return this;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public WalletHistory transactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public WalletHistory transactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getWalletId() {
        return walletId;
    }

    public WalletHistory walletId(String walletId) {
        this.walletId = walletId;
        return this;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getMid() {
        return mid;
    }

    public WalletHistory mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
        this.mid = mid;
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
        WalletHistory walletHistory = (WalletHistory) o;
        if (walletHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), walletHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WalletHistory{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", lastUpdatedPlaypoint=" + getLastUpdatedPlaypoint() +
            ", pricePointValue=" + getPricePointValue() +
            ", transactionType='" + getTransactionType() + "'" +
            ", description='" + getDescription() + "'" +
            ", source='" + getSource() + "'" +
            ", userAction='" + getUserAction() + "'" +
            ", param1='" + getParam1() + "'" +
            ", param2='" + getParam2() + "'" +
            ", param3='" + getParam3() + "'" +
            ", created='" + getCreated() + "'" +
            ", modifiedon='" + getModifiedon() + "'" +
            ", params='" + getParams() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", walletId='" + getWalletId() + "'" +
            ", mid='" + getMid() + "'" +
            "}";
    }
}
