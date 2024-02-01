package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;

import java.math.BigDecimal;

public class InsuranceProductEntityMapper {

    InsuranceProductEntity toEntity(InsuranceProduct insuranceProductDomainObject){
        return new InsuranceProductEntity(
                new InsuranceProductEntityPK(
                        insuranceProductDomainObject.getName(),
                        insuranceProductDomainObject.getInsuranceType().name()
                ),
                insuranceProductDomainObject.getId(),
                BigDecimal.valueOf(insuranceProductDomainObject.getBasePrice()),
                BigDecimal.valueOf(insuranceProductDomainObject.getTaxedPrice())
        );
    }
    InsuranceProduct toDomainObject(InsuranceProductEntity insuranceProductEntity){
        //Cant really get details on taxes here, so we're getting only the category as it is necessary
        return new InsuranceProduct(
                new InsuranceType(insuranceProductEntity.getInsuranceProductEntityPK().getCategory(), 0,0,0),
                insuranceProductEntity.getId(),
                insuranceProductEntity.getInsuranceProductEntityPK().getName(),
                insuranceProductEntity.getBasePrice().doubleValue(),
                insuranceProductEntity.getTaxedPrice().doubleValue()
        );
    }

}
