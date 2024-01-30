package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class InsuranceProductRepositoryGateway implements IInsuranceProductGateway {
    private static final Logger log = LoggerFactory.getLogger(InsuranceProductRepositoryGateway.class);
    // Dependency Injection
    private final InsuranceProductRepository insuranceProductRepository;
    private final InsuranceProductEntityMapper insuranceProductEntityMapper;

    public InsuranceProductRepositoryGateway(InsuranceProductRepository insuranceProductRepository, InsuranceProductEntityMapper insuranceProductEntityMapper) {
        this.insuranceProductRepository = insuranceProductRepository;
        this.insuranceProductEntityMapper = insuranceProductEntityMapper;
    }

    @Override
    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProductDomainObject) {
        log.info("method=createInsuranceProduct, step=starting, name={}", insuranceProductDomainObject);
        InsuranceProductEntity insuranceProductEntity = insuranceProductEntityMapper.toEntity(insuranceProductDomainObject);
        InsuranceProductEntity savedObject = insuranceProductRepository.save(insuranceProductEntity);
        InsuranceProduct result = insuranceProductEntityMapper.toDomainObject(savedObject);
        log.info("method=createInsuranceProduct, step=finished, result={}", result);
        return result;

    }
}
