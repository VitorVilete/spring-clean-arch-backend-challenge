package br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeByNameUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.InsuranceProductDTOMapper;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.PutInsuranceProductResponse;
import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateInsuranceProductUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateInsuranceProductUseCase.class);
    // Dependency injection
    private final IInsuranceProductGateway IInsuranceProductGateway;
    private final InsuranceProductDTOMapper insuranceProductDTOMapper;
    private final FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;


    public CreateInsuranceProductUseCase(IInsuranceProductGateway IInsuranceProductGateway, InsuranceProductDTOMapper insuranceProductDTOMapper, FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase) {
        this.IInsuranceProductGateway = IInsuranceProductGateway;
        this.insuranceProductDTOMapper = insuranceProductDTOMapper;
        this.findInsuranceTypeByNameUseCase = findInsuranceTypeByNameUseCase;
    }
    @Observed(
            name = "user.name",
            contextualName = "CreateInsuranceProductUseCase.createInsuranceProduct",
            lowCardinalityKeyValues = {"customField", "customValue"}
    )
    public PutInsuranceProductResponse createInsuranceProduct(PutInsuranceProductRequest insuranceProductRequest) throws InsuranceTypeNotFoundException {
        log.info("method=createInsuranceProduct, step=starting, name={}", insuranceProductRequest);
        InsuranceType insuranceType = findInsuranceTypeByNameUseCase.findInsuranceTypeByName(insuranceProductRequest.getCategoria());
        InsuranceProduct insuranceProduct = insuranceProductDTOMapper.toInsuranceProductWithInsuranceType(insuranceProductRequest, insuranceType);
        insuranceProduct.setTaxedPrice(calculateTaxes(insuranceProduct));
        InsuranceProduct result = IInsuranceProductGateway.createInsuranceProduct(insuranceProduct);
        PutInsuranceProductResponse response = insuranceProductDTOMapper.toResponse(result);
        log.info("method=createInsuranceProduct, step=finished, name={}", result);
        return response;
    }

    public double calculateTaxes(InsuranceProduct insuranceProduct){
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
}
