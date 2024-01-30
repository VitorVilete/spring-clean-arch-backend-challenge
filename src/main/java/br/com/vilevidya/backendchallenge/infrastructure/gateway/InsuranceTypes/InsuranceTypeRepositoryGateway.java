package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts.InsuranceProductEntityMapper;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeRepository;

public class InsuranceTypeRepositoryGateway implements IInsuranceTypeGateway {
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final InsuranceTypeEntityMapper insuranceProductEntityMapper;

    public InsuranceTypeRepositoryGateway(InsuranceTypeRepository insuranceTypeRepository, InsuranceTypeEntityMapper insuranceProductEntityMapper) {
        this.insuranceTypeRepository = insuranceTypeRepository;
        this.insuranceProductEntityMapper = insuranceProductEntityMapper;
    }


    @Override
    public InsuranceType findInsuranceTypeByName(String insuranceTypeName) {;
        InsuranceTypeEntity foundObject = insuranceTypeRepository.findByName(insuranceTypeName);
        return insuranceProductEntityMapper.toDomainObject(foundObject);
    }
}
