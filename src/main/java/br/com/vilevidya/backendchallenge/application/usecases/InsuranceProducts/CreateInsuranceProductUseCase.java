package br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateInsuranceProductUseCase {
    // Dependency injection
    private final IInsuranceProductGateway IInsuranceProductGateway;

    public CreateInsuranceProductUseCase(IInsuranceProductGateway IInsuranceProductGateway) {
        this.IInsuranceProductGateway = IInsuranceProductGateway;
    }

    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProduct) {
        return IInsuranceProductGateway.createInsuranceProduct(insuranceProduct);
    }
}
