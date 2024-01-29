package br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;

public class CreateInsuranceProductUseCase {
    // Dependency injection
    private IInsuranceProductGateway IInsuranceProductGateway;

    public CreateInsuranceProductUseCase(IInsuranceProductGateway IInsuranceProductGateway) {
        this.IInsuranceProductGateway = IInsuranceProductGateway;
    }

    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProduct) {
        return IInsuranceProductGateway.createInsuranceProduct(insuranceProduct);
    }
}
