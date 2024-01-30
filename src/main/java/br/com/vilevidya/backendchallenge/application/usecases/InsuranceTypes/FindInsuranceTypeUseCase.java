package br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

public class FindInsuranceTypeUseCase {
    private final IInsuranceTypeGateway IInsuranceTypeGateway;

    public FindInsuranceTypeUseCase(IInsuranceTypeGateway IInsuranceTypeGateway) {
        this.IInsuranceTypeGateway = IInsuranceTypeGateway;
    }

    public InsuranceType findInsuranceTypeByName(String insuranceType){
        return IInsuranceTypeGateway.findInsuranceTypeByName(insuranceType);
    }
}
