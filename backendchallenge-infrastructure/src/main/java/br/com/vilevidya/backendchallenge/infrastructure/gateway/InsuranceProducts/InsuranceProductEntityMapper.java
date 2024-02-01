package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;

import java.math.BigDecimal;

public class InsuranceProductEntityMapper {

    InsuranceProductEntity toEntity(InsuranceProduct insuranceProductDomainObject){
        return new InsuranceProductEntity.InsuranceProductEntityBuilder(
                insuranceProductDomainObject.getId(),
                new InsuranceProductEntityPK.InsuranceProductEntityPKBuilder(insuranceProductDomainObject.getName(),
                        insuranceProductDomainObject.getInsuranceType().name()).build(),
                BigDecimal.valueOf(insuranceProductDomainObject.getBasePrice()),
                BigDecimal.valueOf(insuranceProductDomainObject.getTaxedPrice())
        ).build();
    }
    InsuranceProduct toDomainObject(InsuranceProductEntity insuranceProductEntity){
        //Cant really get details on taxes here, so we're getting only the category as it is necessary
        return new InsuranceProduct.InsuranceProductBuilder(
                new InsuranceType.InsuranceTypeBuilder(insuranceProductEntity.getInsuranceProductEntityPK().getCategory(), 0,0,0).build(),
                insuranceProductEntity.getId(),
                insuranceProductEntity.getInsuranceProductEntityPK().getName(),
                insuranceProductEntity.getBasePrice().doubleValue(),
                insuranceProductEntity.getTaxedPrice().doubleValue()
        ).build();
    }

}
