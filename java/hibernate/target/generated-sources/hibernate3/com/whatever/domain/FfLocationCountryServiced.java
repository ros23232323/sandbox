package com.whatever.domain;
// Generated 17-Aug-2016 11:42:42 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FfLocationCountryServiced generated by hbm2java
 */
@Entity
@Table(name="FF_LOCATION_COUNTRY_SERVICED"
    ,schema="FREIGHT_FORWARDER"
)
public class FfLocationCountryServiced  implements java.io.Serializable {


     private BigDecimal id;
     private FreightForwarderLocation freightForwarderLocation;
     private String countryCode;

    public FfLocationCountryServiced() {
    }

    public FfLocationCountryServiced(BigDecimal id, FreightForwarderLocation freightForwarderLocation, String countryCode) {
       this.id = id;
       this.freightForwarderLocation = freightForwarderLocation;
       this.countryCode = countryCode;
    }
   
     @Id 
    
    @Column(name="ID", unique=true, nullable=false, precision=38, scale=0)
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FREIGHT_FORWARDER_LOCATION_ID", nullable=false)
    public FreightForwarderLocation getFreightForwarderLocation() {
        return this.freightForwarderLocation;
    }
    
    public void setFreightForwarderLocation(FreightForwarderLocation freightForwarderLocation) {
        this.freightForwarderLocation = freightForwarderLocation;
    }
    
    @Column(name="COUNTRY_CODE", nullable=false, length=2)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }




}


