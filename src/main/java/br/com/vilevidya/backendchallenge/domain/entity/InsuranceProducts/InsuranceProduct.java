package br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.math.BigDecimal;

public class InsuranceProduct {
    InsuranceType insuranceType;
    String name;
    Number basePrice;
    Number taxedPrice;

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Number basePrice) {
        this.basePrice = basePrice;
    }

    public Number getTaxedPrice() {
        return taxedPrice;
    }

    public void setTaxedPrice(Number taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    public InsuranceProduct(InsuranceType insuranceType, String name, Number basePrice, Number taxedPrice) {
        this.insuranceType = insuranceType;
        this.name = name;
        this.basePrice = basePrice;
        this.taxedPrice = taxedPrice;
    }
}
