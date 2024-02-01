package br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.util.UUID;

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

    public InsuranceProduct toInsuranceProduct(PutInsuranceProductRequest request){
        return new InsuranceProduct.InsuranceProductBuilder(
                new InsuranceType(request.getCategoria(), 0, 0, 0),
             null,
                request.getNome(),
                request.getPreco_base().doubleValue(),
                request.getPreco_tarifado() != null ? request.getPreco_tarifado().doubleValue() : 0
        ).build();
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
