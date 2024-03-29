package br.com.vilevidya.backendchallenge.application.usecases.contracts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

public class InsuranceProductDTOMapper {
    public PutInsuranceProductResponse toResponse(InsuranceProduct insuranceProduct){
        return new PutInsuranceProductResponse(
                insuranceProduct.getId().toString(),
                insuranceProduct.getName(),
                insuranceProduct.getInsuranceType().name(),
                insuranceProduct.getBasePrice(),
                insuranceProduct.getTaxedPrice()
        );
    }

    public InsuranceProduct toInsuranceProductWithInsuranceType(PutInsuranceProductRequest request, InsuranceType insuranceType){
        return new InsuranceProduct.InsuranceProductBuilder(
                insuranceType,
                null,
                request.getNome(),
                request.getPreco_base().doubleValue(),
                request.getPreco_tarifado() != null ? request.getPreco_tarifado().doubleValue() : 0
        ).build();
    }
}
