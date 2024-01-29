package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.IofInsuranceTax;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.IInsuranceTax;

public record IofInsuranceTax(String name, float taxValue) implements IInsuranceTax {

    @Override
    public float getCalculatedTaxedPrice(float basePrice) {
        return taxValue() * basePrice;
    }
}
