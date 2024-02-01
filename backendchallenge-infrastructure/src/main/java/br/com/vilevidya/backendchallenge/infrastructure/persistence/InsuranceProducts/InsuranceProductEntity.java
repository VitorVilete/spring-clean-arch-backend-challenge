package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "INSURANCE_PRODUCT")
public class InsuranceProductEntity {

    @EmbeddedId
    private InsuranceProductEntityPK insuranceProductEntityPK;

    @Column(name = "ID", unique = true, updatable = false)
    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setInsuranceProductEntityPK(InsuranceProductEntityPK insuranceProductEntityPK) {
        this.insuranceProductEntityPK = insuranceProductEntityPK;
    }

    public InsuranceProductEntity(InsuranceProductEntityPK insuranceProductEntityPK, UUID id, BigDecimal basePrice, BigDecimal taxedPrice) {
        this.id = id;
        this.insuranceProductEntityPK = insuranceProductEntityPK;
        this.basePrice = basePrice;
        this.taxedPrice = taxedPrice;
    }

    public InsuranceProductEntity(){

    }
}
