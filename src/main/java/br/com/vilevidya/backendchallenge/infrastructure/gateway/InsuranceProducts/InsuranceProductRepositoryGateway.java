package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;

import java.math.BigDecimal;

public class InsuranceProductRepositoryGateway implements IInsuranceProductGateway {
    // Dependency Injection
    private final InsuranceProductRepository insuranceProductRepository;
    private final InsuranceProductEntityMapper insuranceProductEntityMapper;

    public InsuranceProductRepositoryGateway(InsuranceProductRepository insuranceProductRepository, InsuranceProductEntityMapper insuranceProductEntityMapper) {
        this.insuranceProductRepository = insuranceProductRepository;
        this.insuranceProductEntityMapper = insuranceProductEntityMapper;
    }

    @Override
    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProductDomainObject) {
        InsuranceProductEntity insuranceProductEntity = insuranceProductEntityMapper.toEntity(insuranceProductDomainObject);
        InsuranceProductEntity savedObject = insuranceProductRepository.save(insuranceProductEntity);
        return insuranceProductEntityMapper.toDomainObject(savedObject);

    }
}
