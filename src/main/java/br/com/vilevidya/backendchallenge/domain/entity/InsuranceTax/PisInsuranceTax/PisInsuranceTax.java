package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTax.PisInsuranceTax;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTax.IInsuranceTax;

public record PisInsuranceTax(String name, float taxValue) implements IInsuranceTax {

    @Override
    public float getCalculatedTaxedPrice(float basePrice) {
        return taxValue() * basePrice;
    }
}
