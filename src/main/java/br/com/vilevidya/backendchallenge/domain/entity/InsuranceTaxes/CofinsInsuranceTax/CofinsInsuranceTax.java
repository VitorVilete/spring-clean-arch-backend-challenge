package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.CofinsInsuranceTax;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.IInsuranceTax;

public record CofinsInsuranceTax(String name, float taxValue) implements IInsuranceTax {

    @Override
    public float getCalculatedTaxedPrice(float basePrice) {
        return taxValue() * basePrice;
    }
}
