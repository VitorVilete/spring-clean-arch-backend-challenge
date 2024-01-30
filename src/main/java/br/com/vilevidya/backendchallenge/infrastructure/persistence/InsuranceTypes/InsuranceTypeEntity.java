package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "INSURANCE_TYPE")
public class InsuranceTypeEntity {
    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "IOF_TAX_VALUE", precision = 11, scale = 2)
    private BigDecimal iofTaxValue;

    @Column(name = "PIS_TAX_VALUE", precision = 11, scale = 2)
    private BigDecimal pisTaxValue;

    @Column(name = "COFINS_TAX_VALUE", precision = 11, scale = 2)
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

    public InsuranceTypeEntity(String name, BigDecimal iofTaxValue, BigDecimal pisTaxValue, BigDecimal cofinsTaxValue) {
        this.name = name;
        this.iofTaxValue = iofTaxValue;
        this.pisTaxValue = pisTaxValue;
        this.cofinsTaxValue = cofinsTaxValue;
    }
}
