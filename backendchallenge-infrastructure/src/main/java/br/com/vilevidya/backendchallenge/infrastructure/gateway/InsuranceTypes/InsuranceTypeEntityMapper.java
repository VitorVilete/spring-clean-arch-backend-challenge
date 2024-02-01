package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;

import java.math.BigDecimal;


public class InsuranceTypeEntityMapper {
    public InsuranceTypeLocalEntity toLocalEntity(InsuranceType insuranceProductDomainObject){
        return new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder(
                insuranceProductDomainObject.name(),
                BigDecimal.valueOf(insuranceProductDomainObject.iofTaxValue().doubleValue()),
                BigDecimal.valueOf(insuranceProductDomainObject.pisTaxValue().doubleValue()),
                BigDecimal.valueOf(insuranceProductDomainObject.cofinsTaxValue().doubleValue())
        ).build();

    }
    public InsuranceType toDomainObject(InsuranceTypeLocalEntity insuranceTypeLocalEntity){
        return new InsuranceType.InsuranceTypeBuilder(
                insuranceTypeLocalEntity.getName(),
                insuranceTypeLocalEntity.getIofTaxValue(),
                insuranceTypeLocalEntity.getPisTaxValue(),
                insuranceTypeLocalEntity.getCofinsTaxValue()
        ).build();
    }
}
