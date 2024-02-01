package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;

import java.math.BigDecimal;

public class InsuranceTypeLocalEntity {

    private String name;

    private BigDecimal iofTaxValue;

    private BigDecimal pisTaxValue;

    private BigDecimal cofinsTaxValue;

    public String getName() {
        return name;
    }

    public BigDecimal getIofTaxValue() {
        return iofTaxValue;
    }

    public BigDecimal getPisTaxValue() {
        return pisTaxValue;
    }

    public BigDecimal getCofinsTaxValue() {
        return cofinsTaxValue;
    }

    public InsuranceTypeLocalEntity(InsuranceTypeLocalEntityBuilder insuranceTypeLocalEntityBuilder) {
        this.name = insuranceTypeLocalEntityBuilder.name;
        this.iofTaxValue = insuranceTypeLocalEntityBuilder.iofTaxValue;
        this.pisTaxValue = insuranceTypeLocalEntityBuilder.pisTaxValue;
        this.cofinsTaxValue = insuranceTypeLocalEntityBuilder.cofinsTaxValue;
    }

    public InsuranceTypeLocalEntity() {
    }

    public static class InsuranceTypeLocalEntityBuilder{
        private String name;

        private BigDecimal iofTaxValue;

        private BigDecimal pisTaxValue;

        private BigDecimal cofinsTaxValue;
        public InsuranceTypeLocalEntityBuilder(){

        }
        public InsuranceTypeLocalEntityBuilder(String name, BigDecimal iofTaxValue, BigDecimal pisTaxValue, BigDecimal cofinsTaxValue){
            this.name = name;
            this.iofTaxValue = iofTaxValue;
            this.pisTaxValue = pisTaxValue;
            this.cofinsTaxValue = cofinsTaxValue;
        }
        public InsuranceTypeLocalEntityBuilder setName(String name){
            this.name = name;
            return this;
        }
        public InsuranceTypeLocalEntityBuilder setIofTaxValue(BigDecimal iofTaxValue){
            this.iofTaxValue = iofTaxValue;
            return this;
        }
        public InsuranceTypeLocalEntityBuilder setPisTaxValue(BigDecimal pisTaxValue){
            this.pisTaxValue = pisTaxValue;
            return this;
        }
        public InsuranceTypeLocalEntityBuilder setCofinsTaxValue(BigDecimal cofinsTaxValue){
            this.cofinsTaxValue = cofinsTaxValue;
            return this;
        }
        public InsuranceTypeLocalEntity build(){
            return new InsuranceTypeLocalEntity(this);
        }
    }
}
