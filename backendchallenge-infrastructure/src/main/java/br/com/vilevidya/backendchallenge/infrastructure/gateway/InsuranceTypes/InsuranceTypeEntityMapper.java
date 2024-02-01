package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;

import java.math.BigDecimal;


public class InsuranceTypeEntityMapper {
    public InsuranceTypeLocalEntity toLocalEntity(InsuranceType insuranceProductDomainObject){
        return new InsuranceTypeLocalEntity(
                insuranceProductDomainObject.name(),
                BigDecimal.valueOf(insuranceProductDomainObject.iofTaxValue().doubleValue()),
                BigDecimal.valueOf(insuranceProductDomainObject.pisTaxValue().doubleValue()),
                BigDecimal.valueOf(insuranceProductDomainObject.cofinsTaxValue().doubleValue())
        );

    }
    public InsuranceType toDomainObject(InsuranceTypeLocalEntity insuranceTypeLocalEntity){
        return new InsuranceType(
                insuranceTypeLocalEntity.getName(),
                insuranceTypeLocalEntity.getIofTaxValue(),
                insuranceTypeLocalEntity.getPisTaxValue(),
                insuranceTypeLocalEntity.getCofinsTaxValue()
        );
    }
}
