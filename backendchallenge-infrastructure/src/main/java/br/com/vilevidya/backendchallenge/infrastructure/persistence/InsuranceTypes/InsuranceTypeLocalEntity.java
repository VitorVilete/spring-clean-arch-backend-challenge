package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import java.math.BigDecimal;

public class InsuranceTypeLocalEntity {

    private String name;

    private BigDecimal iofTaxValue;

    private BigDecimal pisTaxValue;

    private BigDecimal cofinsTaxValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIofTaxValue() {
        return iofTaxValue;
    }

    public void setIofTaxValue(BigDecimal iofTaxValue) {
        this.iofTaxValue = iofTaxValue;
    }

    public BigDecimal getPisTaxValue() {
        return pisTaxValue;
    }

    public void setPisTaxValue(BigDecimal pisTaxValue) {
        this.pisTaxValue = pisTaxValue;
    }

    public BigDecimal getCofinsTaxValue() {
        return cofinsTaxValue;
    }

    public void setCofinsTaxValue(BigDecimal cofinsTaxValue) {
        this.cofinsTaxValue = cofinsTaxValue;
    }

    public InsuranceTypeLocalEntity(String name, BigDecimal iofTaxValue, BigDecimal pisTaxValue, BigDecimal cofinsTaxValue) {
        this.name = name;
        this.iofTaxValue = iofTaxValue;
        this.pisTaxValue = pisTaxValue;
        this.cofinsTaxValue = cofinsTaxValue;
    }

    public InsuranceTypeLocalEntity() {
    }
}
