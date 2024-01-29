package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;

import java.math.BigDecimal;

public class InsuranceProductRepositoryGateway implements IInsuranceProductGateway {
    // Dependency Injection
    private final InsuranceProductRepository insuranceProductRepository;
    private final InsuranceProductMapper insuranceProductMapper;

    public InsuranceProductRepositoryGateway(InsuranceProductRepository insuranceProductRepository, InsuranceProductMapper insuranceProductMapper) {
        this.insuranceProductRepository = insuranceProductRepository;
        this.insuranceProductMapper = insuranceProductMapper;
    }

    @Override
    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProductDomainObject) {
        InsuranceProductEntity insuranceProductEntity = insuranceProductMapper.toEntity(insuranceProductDomainObject);
        InsuranceProductEntity savedObject = insuranceProductRepository.save(insuranceProductEntity);
        return insuranceProductMapper.toDomainObject(savedObject);

    }
}
