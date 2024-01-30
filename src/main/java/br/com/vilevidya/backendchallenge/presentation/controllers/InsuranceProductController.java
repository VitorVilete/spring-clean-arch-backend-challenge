package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductResponse;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class InsuranceProductController {
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
        InsuranceType insuranceTypeObject = insuranceTypeGateway.findInsuranceProductByName(request.categoria());
        InsuranceProduct insuranceProductObject = insuranceProductDTOMapper.toInsuranceProductWithInsuranceType(request, insuranceTypeObject);
        insuranceProductObject.setTaxedPrice(calculateTaxes(insuranceProductObject));
        InsuranceProduct insuranceProduct =  insuranceProductGateway.createInsuranceProduct(insuranceProductObject);

        return insuranceProductDTOMapper.toResponse(insuranceProduct);
    }

    private Number calculateTaxes(InsuranceProduct insuranceProduct){
        if (insuranceProduct.getTaxedPrice() == null){
            double basePrice = insuranceProduct.getBasePrice().doubleValue();
            InsuranceType insuranceType = insuranceProduct.getInsuranceType();

            return basePrice +
                    (basePrice * insuranceType.iofTaxValue().doubleValue()) +
                    (basePrice * insuranceType.pisTaxValue().doubleValue()) +
                    (basePrice * insuranceType.cofinsTaxValue().doubleValue());
        }else{
            return insuranceProduct.getTaxedPrice();
        }

    }

}
