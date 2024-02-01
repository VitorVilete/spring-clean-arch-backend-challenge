package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsuranceTypeRepositoryGateway implements IInsuranceTypeGateway {

    private static final Logger log = LoggerFactory.getLogger(InsuranceTypeRepositoryGateway.class);

    private final InsuranceTypeRepository insuranceTypeRepository;
    private final InsuranceTypeEntityMapper insuranceProductEntityMapper;

    public InsuranceTypeRepositoryGateway(InsuranceTypeRepository insuranceTypeRepository, InsuranceTypeEntityMapper insuranceProductEntityMapper) {
        this.insuranceTypeRepository = insuranceTypeRepository;
        this.insuranceProductEntityMapper = insuranceProductEntityMapper;
    }


    @Override
    public InsuranceType findInsuranceTypeByName(String insuranceTypeName) throws InsuranceTypeNotFoundException {
        log.info("method=findInsuranceTypeByName, step=starting, name={}", insuranceTypeName);
        InsuranceTypeEntity foundObject = insuranceTypeRepository.findByName(insuranceTypeName);
        if(foundObject != null) {
            InsuranceType result = insuranceProductEntityMapper.toDomainObject(foundObject);
            log.info("method=findInsuranceTypeByName, step=finished, result={}", result);
            return result;
        }else {
            throw new InsuranceTypeNotFoundException("Categoria não encontrada com o nome: "+ insuranceTypeName);
        }

    }
}
