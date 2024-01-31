package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeByNameUseCase;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductResponse;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PutInsuranceProductResponse> create(@RequestBody @Valid PutInsuranceProductRequest request){
        log.info("method=create, step=starting, request={}", request);
        PutInsuranceProductResponse response = insuranceProductDTOMapper.toResponse(
                createInsuranceProductUseCase.createInsuranceProduct(
                    insuranceProductDTOMapper.toInsuranceProductWithInsuranceType(
                            request,
                            findInsuranceTypeByNameUseCase.findInsuranceTypeByName(request.getCategoria())
                    )
        ));
        log.info("method=create, step=finished, response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
