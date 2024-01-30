package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductResponse;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class InsuranceProductController {

    private static final Logger log = LoggerFactory.getLogger(InsuranceProductController.class);
    private final IInsuranceProductGateway insuranceProductGateway;
    private final IInsuranceTypeGateway insuranceTypeGateway;
    private final InsuranceProductDTOMapper insuranceProductDTOMapper;

    public InsuranceProductController(IInsuranceProductGateway insuranceProductGateway, IInsuranceTypeGateway insuranceTypeGateway, br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper insuranceProductDTOMapper) {
        this.insuranceProductGateway = insuranceProductGateway;
        this.insuranceTypeGateway = insuranceTypeGateway;
        this.insuranceProductDTOMapper = insuranceProductDTOMapper;
    }

    @PutMapping
    PutInsuranceProductResponse create(@RequestBody PutInsuranceProductRequest request){
        log.info("method=create, step=starting, request={}", request);
        InsuranceType insuranceTypeObject = insuranceTypeGateway.findInsuranceTypeByName(request.categoria());
        InsuranceProduct insuranceProductObject = insuranceProductDTOMapper.toInsuranceProductWithInsuranceType(request, insuranceTypeObject);
        insuranceProductObject.setTaxedPrice(calculateTaxes(insuranceProductObject));
        InsuranceProduct insuranceProduct =  insuranceProductGateway.createInsuranceProduct(insuranceProductObject);

        PutInsuranceProductResponse response = insuranceProductDTOMapper.toResponse(insuranceProduct);
        log.info("method=create, step=finished, response={}", response);
        return response;
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

}
