package br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.math.BigDecimal;

public class InsuranceProductDTOMapper {
    public PutInsuranceProductResponse toResponse(InsuranceProduct insuranceProduct){
        return new PutInsuranceProductResponse(
                // TODO: Return correlation-id for the id
                123L,
                insuranceProduct.name(),
                insuranceProduct.insuranceType().name(),
                insuranceProduct.basePrice(),
                insuranceProduct.taxedPrice()
        );
    }

    public InsuranceProduct toInsuranceProduct(PutInsuranceProductRequest request){
        return new InsuranceProduct(
                new InsuranceType(request.categoria(), null, null, null),
                request.nome(),
                new BigDecimal(request.preco_base().toString()),
                request.preco_tarifado() != null ? new BigDecimal(request.preco_tarifado().toString()) : null
                );
    }

    public InsuranceProduct toInsuranceProductWithInsuranceType(PutInsuranceProductRequest request, InsuranceType insuranceType){
        return new InsuranceProduct(
                insuranceType,
                request.nome(),
                new BigDecimal(request.preco_base().toString()),
                request.preco_tarifado() != null ? new BigDecimal(request.preco_tarifado().toString()) : null
        );
    }
}
