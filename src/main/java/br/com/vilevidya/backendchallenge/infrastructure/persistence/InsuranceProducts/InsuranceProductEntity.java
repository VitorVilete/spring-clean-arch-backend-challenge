package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "INSURANCE_PRODUCT")
public class InsuranceProductEntity {

    @EmbeddedId
    private InsuranceProductEntityPK insuranceProductEntityPK;
    @Column(name = "BASE_PRICE", precision = 11, scale = 2)
    private BigDecimal basePrice;
    @Column(name = "TAXED_PRICE", precision = 11, scale = 2)
    private BigDecimal taxedPrice;


    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public void setTaxedPrice(BigDecimal taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    public InsuranceProductEntityPK getInsuranceProductEntityPK() {
        return insuranceProductEntityPK;
    }

    public void setInsuranceProductEntityPK(InsuranceProductEntityPK insuranceProductEntityPK) {
        this.insuranceProductEntityPK = insuranceProductEntityPK;
    }

    public InsuranceProductEntity(InsuranceProductEntityPK insuranceProductEntityPK, BigDecimal basePrice, BigDecimal taxedPrice) {
        this.insuranceProductEntityPK = insuranceProductEntityPK;
        this.basePrice = basePrice;
        this.taxedPrice = taxedPrice;
    }

    public InsuranceProductEntity(){

    }
}
