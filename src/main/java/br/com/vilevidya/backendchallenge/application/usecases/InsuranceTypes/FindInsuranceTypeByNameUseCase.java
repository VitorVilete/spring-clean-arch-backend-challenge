package br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindInsuranceTypeByNameUseCase {
    private static final Logger log = LoggerFactory.getLogger(FindInsuranceTypeByNameUseCase.class);
    private final IInsuranceTypeGateway IInsuranceTypeGateway;

    public FindInsuranceTypeByNameUseCase(IInsuranceTypeGateway IInsuranceTypeGateway) {
        this.IInsuranceTypeGateway = IInsuranceTypeGateway;
    }

    public InsuranceType findInsuranceTypeByName(String insuranceType){
        log.info("method=findInsuranceTypeByName, step=starting, name={}", insuranceType);
        InsuranceType result = IInsuranceTypeGateway.findInsuranceTypeByName(insuranceType);
        log.info("method=findInsuranceTypeByName, step=finished, result={}", result);
        return result;
    }
}
