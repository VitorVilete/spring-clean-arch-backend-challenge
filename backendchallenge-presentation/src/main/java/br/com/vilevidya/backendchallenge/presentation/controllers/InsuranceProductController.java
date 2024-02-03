package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.PutInsuranceProductResponse;
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

    public InsuranceProductController(CreateInsuranceProductUseCase createInsuranceProductUseCase) {
        this.createInsuranceProductUseCase = createInsuranceProductUseCase;
    }

    @PutMapping
    @Observed(
            name = "user.name",
            contextualName = "InsuranceProductController.create",
            lowCardinalityKeyValues = {"customField", "customValue"}
    )
    public ResponseEntity<PutInsuranceProductResponse> create(@RequestBody @Valid PutInsuranceProductRequest request) throws Exception {
        log.info("method=create, step=starting, request={}", request);
        PutInsuranceProductResponse response = createInsuranceProductUseCase.createInsuranceProduct(request);
        log.info("method=create, step=finished, response={}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
