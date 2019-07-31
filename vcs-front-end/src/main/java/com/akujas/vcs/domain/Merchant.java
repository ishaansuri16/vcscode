package com.akujas.vcs.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Merchant.
 */
@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "mid")
    private String mid;

    @Column(name = "params")
    private String params;

    @Column(name = "param1")
    private String param1;

    @Column(name = "param2")
    private String param2;

    @Column(name = "param3")
    private String param3;

    @Column(name = "mid_secret")
    private String midSecret;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created")
    private Instant created;

    @Column(name = "modifiedon")
    private Instant modifiedon;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "address")
    private String address;

    @Column(name = "isenabled")
    private Boolean isenabled;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Merchant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Merchant displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public Merchant description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMid() {
        return mid;
    }

    public Merchant mid(String mid) {
        this.mid = mid;
        return this;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getParams() {
        return params;
    }

    public Merchant params(String params) {
        this.params = params;
        return this;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getParam1() {
        return param1;
    }

    public Merchant param1(String param1) {
        this.param1 = param1;
        return this;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public Merchant param2(String param2) {
        this.param2 = param2;
        return this;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public Merchant param3(String param3) {
        this.param3 = param3;
        return this;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getMidSecret() {
        return midSecret;
    }

    public Merchant midSecret(String midSecret) {
        this.midSecret = midSecret;
        return this;
    }

    public void setMidSecret(String midSecret) {
        this.midSecret = midSecret;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Merchant createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreated() {
        return created;
    }

    public Merchant created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModifiedon() {
        return modifiedon;
    }

    public Merchant modifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
        return this;
    }

    public void setModifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
    }

    public String getPincode() {
        return pincode;
    }

    public Merchant pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public Merchant address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isIsenabled() {
        return isenabled;
    }

    public Merchant isenabled(Boolean isenabled) {
        this.isenabled = isenabled;
        return this;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
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
        Merchant merchant = (Merchant) o;
        if (merchant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), merchant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Merchant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", description='" + getDescription() + "'" +
            ", mid='" + getMid() + "'" +
            ", params='" + getParams() + "'" +
            ", param1='" + getParam1() + "'" +
            ", param2='" + getParam2() + "'" +
            ", param3='" + getParam3() + "'" +
            ", midSecret='" + getMidSecret() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", created='" + getCreated() + "'" +
            ", modifiedon='" + getModifiedon() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", address='" + getAddress() + "'" +
            ", isenabled='" + isIsenabled() + "'" +
            "}";
    }
}
