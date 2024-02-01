package br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

public interface IInsuranceTypeGateway {
    InsuranceType findInsuranceTypeByName(String insuranceTypeName) throws InsuranceTypeNotFoundException;
}
