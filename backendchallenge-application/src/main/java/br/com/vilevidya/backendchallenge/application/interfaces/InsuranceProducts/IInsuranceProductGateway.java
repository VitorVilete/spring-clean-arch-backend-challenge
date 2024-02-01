package br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;

public interface IInsuranceProductGateway {
    InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProduct);
}
