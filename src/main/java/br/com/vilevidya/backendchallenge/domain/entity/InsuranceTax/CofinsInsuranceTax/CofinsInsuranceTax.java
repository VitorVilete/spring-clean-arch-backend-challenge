package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTax.CofinsInsuranceTax;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTax.IInsuranceTax;

public record CofinsInsuranceTax(String name, float taxValue) implements IInsuranceTax {

    @Override
    public float getCalculatedTaxedPrice(float basePrice) {
        return taxValue() * basePrice;
    }
}
