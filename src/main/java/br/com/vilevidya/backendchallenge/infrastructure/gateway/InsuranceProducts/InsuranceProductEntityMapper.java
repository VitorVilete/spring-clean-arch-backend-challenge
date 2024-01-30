package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;

public class InsuranceProductEntityMapper {
    InsuranceProductEntity toEntity(InsuranceProduct insuranceProductDomainObject){
        return new InsuranceProductEntity(
                insuranceProductDomainObject.name(),
                insuranceProductDomainObject.insuranceType().name(),
                insuranceProductDomainObject.basePrice(),
                insuranceProductDomainObject.taxedPrice()
        );
    }
    InsuranceProduct toDomainObject(InsuranceProductEntity insuranceProductEntity){
        //Cant really get details on taxes here, so we're getting only the category as it is necessary
        return new InsuranceProduct(
                null,
                insuranceProductEntity.getName(),
                insuranceProductEntity.getBasePrice(),
                insuranceProductEntity.getTaxedPrice()
        );
    }

}
