package br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.exceptions.InsuranceTypeNotFoundException;

public interface IInsuranceTypeGateway {
    InsuranceType findInsuranceTypeByName(String insuranceTypeName) throws InsuranceTypeNotFoundException;
}
