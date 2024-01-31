package br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CreateInsuranceProductUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateInsuranceProductUseCase.class);
    // Dependency injection
    private final IInsuranceProductGateway IInsuranceProductGateway;

    public CreateInsuranceProductUseCase(IInsuranceProductGateway IInsuranceProductGateway) {
        this.IInsuranceProductGateway = IInsuranceProductGateway;
    }

    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProduct) {
        log.info("method=createInsuranceProduct, step=starting, name={}", insuranceProduct);
        insuranceProduct.setTaxedPrice(calculateTaxes(insuranceProduct));
        InsuranceProduct result = IInsuranceProductGateway.createInsuranceProduct(insuranceProduct);
        log.info("method=createInsuranceProduct, step=finished, name={}", insuranceProduct);
        return result;
    }

    private double calculateTaxes(InsuranceProduct insuranceProduct){
        double result;
        if (insuranceProduct.getTaxedPrice() == 0){
            double basePrice = insuranceProduct.getBasePrice();
            InsuranceType insuranceType = insuranceProduct.getInsuranceType();

            result = basePrice +
                    (basePrice * insuranceType.iofTaxValue().doubleValue()) +
                    (basePrice * insuranceType.pisTaxValue().doubleValue()) +
                    (basePrice * insuranceType.cofinsTaxValue().doubleValue());
        }else{
            result = insuranceProduct.getTaxedPrice();
        }
        return result;
    }

    public class InvalidInsuranceProductException extends Exception {
        public  InvalidInsuranceProductException(String errorMesage){
            super(errorMesage);
        }
    }
}
