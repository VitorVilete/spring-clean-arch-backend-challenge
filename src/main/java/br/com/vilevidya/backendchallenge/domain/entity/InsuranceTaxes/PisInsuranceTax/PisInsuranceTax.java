package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.PisInsuranceTax;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.IInsuranceTax;

public record PisInsuranceTax(String name, float taxValue) implements IInsuranceTax {

    @Override
    public float getCalculatedTaxedPrice(float basePrice) {
        return taxValue() * basePrice;
    }
}
