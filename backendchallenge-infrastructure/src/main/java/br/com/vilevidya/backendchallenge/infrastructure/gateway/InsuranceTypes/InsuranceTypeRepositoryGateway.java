package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InsuranceTypeRepositoryGateway implements IInsuranceTypeGateway {

    private static final Logger log = LoggerFactory.getLogger(InsuranceTypeRepositoryGateway.class);
    private final InsuranceTypeLocalRepository insuranceTypeLocalRepository;
    private final InsuranceTypeEntityMapper insuranceProductEntityMapper;

    public InsuranceTypeRepositoryGateway(InsuranceTypeLocalRepository insuranceTypeLocalRepository, InsuranceTypeEntityMapper insuranceProductEntityMapper) {
        this.insuranceTypeLocalRepository = insuranceTypeLocalRepository;
        this.insuranceProductEntityMapper = insuranceProductEntityMapper;
    }


    @Override
    public InsuranceType findInsuranceTypeByName(String insuranceTypeName) throws InsuranceTypeNotFoundException {
        log.info("method=findInsuranceTypeByName, step=starting, name={}", insuranceTypeName);
        InsuranceTypeLocalEntity foundObject = insuranceTypeLocalRepository.findByName(insuranceTypeName);
        InsuranceType result = insuranceProductEntityMapper.toDomainObject(foundObject);
        log.info("method=findInsuranceTypeByName, step=finished, result={}", result);
        return result;

    }
}
