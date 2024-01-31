package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeEntity;

import java.math.BigDecimal;


public class InsuranceTypeEntityMapper {
    public InsuranceTypeEntity toEntity(InsuranceType insuranceProductDomainObject){
        return new InsuranceTypeEntity(
                insuranceProductDomainObject.name(),
                BigDecimal.valueOf(insuranceProductDomainObject.iofTaxValue().doubleValue()),
                BigDecimal.valueOf(insuranceProductDomainObject.pisTaxValue().doubleValue()),
                BigDecimal.valueOf(insuranceProductDomainObject.cofinsTaxValue().doubleValue())
        );

    }
    public InsuranceType toDomainObject(InsuranceTypeEntity insuranceTypeEntity){
        return new InsuranceType(
                insuranceTypeEntity.getName(),
                insuranceTypeEntity.getIofTaxValue(),
                insuranceTypeEntity.getPisTaxValue(),
                insuranceTypeEntity.getCofinsTaxValue()
        );
    }
}
