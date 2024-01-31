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
        return new InsuranceProduct(
                new InsuranceType(request.categoria(), 0, 0, 0),
                request.id() != null ? UUID.fromString(request.id()) : null,
                request.nome(),
                request.preco_base().doubleValue(),
                request.preco_tarifado() != null ? request.preco_tarifado().doubleValue() : 0
                );
    }

    public InsuranceProduct toInsuranceProductWithInsuranceType(PutInsuranceProductRequest request, InsuranceType insuranceType){
        return new InsuranceProduct(
                insuranceType,
                request.id() != null ? UUID.fromString(request.id()) : null,
                request.nome(),
                request.preco_base().doubleValue(),
                request.preco_tarifado() != null ? request.preco_tarifado().doubleValue() : 0
        );
    }
}
