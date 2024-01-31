package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeByNameUseCase;
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
    private final CreateInsuranceProductUseCase createInsuranceProductUseCase;
    private final FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;
    private final InsuranceProductDTOMapper insuranceProductDTOMapper;

    public InsuranceProductController(IInsuranceProductGateway insuranceProductGateway, IInsuranceTypeGateway insuranceTypeGateway, CreateInsuranceProductUseCase createInsuranceProductUseCase, FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase, br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper insuranceProductDTOMapper) {
        this.createInsuranceProductUseCase = createInsuranceProductUseCase;
        this.findInsuranceTypeByNameUseCase = findInsuranceTypeByNameUseCase;
        this.insuranceProductDTOMapper = insuranceProductDTOMapper;
    }

    @PutMapping
    PutInsuranceProductResponse create(@RequestBody PutInsuranceProductRequest request){
        log.info("method=create, step=starting, request={}", request);
        PutInsuranceProductResponse response = insuranceProductDTOMapper.toResponse(
                createInsuranceProductUseCase.createInsuranceProduct(
                    insuranceProductDTOMapper.toInsuranceProductWithInsuranceType(
                            request,
                            findInsuranceTypeByNameUseCase.findInsuranceTypeByName(request.categoria())
                    )
        ));
        log.info("method=create, step=finished, response={}", response);
        return response;
    }

//    private double calculateTaxes(InsuranceProduct insuranceProduct){
//        double result;
//        if (insuranceProduct.getTaxedPrice() == 0){
//            double basePrice = insuranceProduct.getBasePrice();
//            InsuranceType insuranceType = insuranceProduct.getInsuranceType();
//
//            result = basePrice +
//                    (basePrice * insuranceType.iofTaxValue().doubleValue()) +
//                    (basePrice * insuranceType.pisTaxValue().doubleValue()) +
//                    (basePrice * insuranceType.cofinsTaxValue().doubleValue());
//        }else{
//            result = insuranceProduct.getTaxedPrice();
//        }
//        return result;
//    }

}
