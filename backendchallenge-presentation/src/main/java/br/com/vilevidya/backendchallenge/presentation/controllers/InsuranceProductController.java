package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeByNameUseCase;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductResponse;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
public class InsuranceProductController {

    private static final Logger log = LoggerFactory.getLogger(InsuranceProductController.class);
    private final CreateInsuranceProductUseCase createInsuranceProductUseCase;
    private final FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;
    private final InsuranceProductDTOMapper insuranceProductDTOMapper;

    public InsuranceProductController(CreateInsuranceProductUseCase createInsuranceProductUseCase, FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase, br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper insuranceProductDTOMapper) {
        this.createInsuranceProductUseCase = createInsuranceProductUseCase;
        this.findInsuranceTypeByNameUseCase = findInsuranceTypeByNameUseCase;
        this.insuranceProductDTOMapper = insuranceProductDTOMapper;
    }

    @PutMapping
    @Observed(
            name = "user.name",
            contextualName = "InsuranceProductController.create",
            lowCardinalityKeyValues = {"customField", "customValue"}
    )
    public ResponseEntity<PutInsuranceProductResponse> create(@RequestBody @Valid PutInsuranceProductRequest request) throws Exception {
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
