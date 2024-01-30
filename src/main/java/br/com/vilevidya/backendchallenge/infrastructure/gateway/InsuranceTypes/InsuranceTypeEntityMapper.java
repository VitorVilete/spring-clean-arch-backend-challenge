package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeEntity;


public class InsuranceTypeEntityMapper {
    InsuranceTypeEntity toEntity(InsuranceType insuranceProductDomainObject){
        return new InsuranceTypeEntity(
                insuranceProductDomainObject.name(),
                insuranceProductDomainObject.iofTaxValue(),
                insuranceProductDomainObject.pisTaxValue(),
                insuranceProductDomainObject.cofinsTaxValue()
        );

    }
    InsuranceType toDomainObject(InsuranceTypeEntity insuranceTypeEntity){
        return new InsuranceType(
                insuranceTypeEntity.getName(),
                insuranceTypeEntity.getIofTaxValue(),
                insuranceTypeEntity.getPisTaxValue(),
                insuranceTypeEntity.getCofinsTaxValue()
        );
    }
}
