package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductResponse;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class InsuranceProductController {
    private final IInsuranceProductGateway insuranceProductGateway;
    private final InsuranceProductDTOMapper InsuranceProductDTOMapper;

    public InsuranceProductController(IInsuranceProductGateway insuranceProductGateway, br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper insuranceProductDTOMapper) {
        this.insuranceProductGateway = insuranceProductGateway;
        InsuranceProductDTOMapper = insuranceProductDTOMapper;
    }

    @PutMapping
    PutInsuranceProductResponse create(@RequestBody PutInsuranceProductRequest request){
        InsuranceProduct insuranceProductObject = InsuranceProductDTOMapper.toInsuranceProduct(request);
        InsuranceProduct insuranceProduct =  insuranceProductGateway.createInsuranceProduct(insuranceProductObject);

        return InsuranceProductDTOMapper.toResponse(insuranceProduct);
    }

}
