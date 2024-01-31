package br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindInsuranceTypeByNameUseCase {
    private static final Logger log = LoggerFactory.getLogger(FindInsuranceTypeByNameUseCase.class);
    private final IInsuranceTypeGateway IInsuranceTypeGateway;

    public FindInsuranceTypeByNameUseCase(IInsuranceTypeGateway IInsuranceTypeGateway) {
        this.IInsuranceTypeGateway = IInsuranceTypeGateway;
    }

    public InsuranceType findInsuranceTypeByName(String insuranceType) throws InsuranceTypeNotFoundException {
        log.info("method=findInsuranceTypeByName, step=starting, name={}", insuranceType);
        InsuranceType result = IInsuranceTypeGateway.findInsuranceTypeByName(insuranceType);
        if(result != null){
        log.info("method=findInsuranceTypeByName, step=finished, result={}", result);
        return result;
        }else{
            throw new InsuranceTypeNotFoundException("Categoria não encontrada com o nome: "+insuranceType);
        }
    }
}
