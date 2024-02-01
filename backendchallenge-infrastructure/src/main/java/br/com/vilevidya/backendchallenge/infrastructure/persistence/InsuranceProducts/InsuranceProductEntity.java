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

    public InsuranceProductEntityPK getInsuranceProductEntityPK() {
        return insuranceProductEntityPK;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public InsuranceProductEntity(){

    }
    public InsuranceProductEntity(InsuranceProductEntityBuilder insuranceProductEntityBuilder) {
        this.id = insuranceProductEntityBuilder.id;
        this.insuranceProductEntityPK = insuranceProductEntityBuilder.insuranceProductEntityPK;
        this.basePrice = insuranceProductEntityBuilder.basePrice;
        this.taxedPrice = insuranceProductEntityBuilder.taxedPrice;
    }


    // Builder
    public static class InsuranceProductEntityBuilder{
        //Required
        private UUID id;
        private InsuranceProductEntityPK insuranceProductEntityPK;
        private BigDecimal basePrice;
        private BigDecimal taxedPrice;

        public InsuranceProductEntityBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public InsuranceProductEntityBuilder setInsuranceProductEntityPK(InsuranceProductEntityPK insuranceProductEntityPK) {
            this.insuranceProductEntityPK = insuranceProductEntityPK;
            return this;
        }

        public InsuranceProductEntityBuilder setBasePrice(BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public InsuranceProductEntityBuilder setTaxedPrice(BigDecimal taxedPrice) {
            this.taxedPrice = taxedPrice;
            return this;
        }

        public InsuranceProductEntityBuilder(){
        }

        public InsuranceProductEntityBuilder(UUID id, InsuranceProductEntityPK insuranceProductEntityPK, BigDecimal basePrice, BigDecimal taxedPrice){
            this.id = id;
            this.insuranceProductEntityPK = insuranceProductEntityPK;
            this.basePrice = basePrice;
            this.taxedPrice = taxedPrice;
        }
        public InsuranceProductEntity build(){
            return new InsuranceProductEntity(this);
        }
    }
}
