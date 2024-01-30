package br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

public interface IInsuranceTypeGateway {
    InsuranceType findInsuranceProductByName(String insuranceTypeName);
}
