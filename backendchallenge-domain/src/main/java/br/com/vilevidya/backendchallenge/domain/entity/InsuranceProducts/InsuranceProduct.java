package br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.util.UUID;


public class InsuranceProduct {
    InsuranceType insuranceType;

    UUID id;
    String name;
    double basePrice;
    double taxedPrice;

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

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getTaxedPrice() {
        return taxedPrice;
    }

    public void setTaxedPrice(double taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public InsuranceProduct(InsuranceType insuranceType, UUID id, String name, double basePrice, double taxedPrice) {
        this.insuranceType = insuranceType;
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.taxedPrice = taxedPrice;
    }
}
