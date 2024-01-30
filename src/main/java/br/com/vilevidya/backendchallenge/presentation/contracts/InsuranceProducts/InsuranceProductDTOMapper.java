package br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.IInsuranceTax;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.math.BigDecimal;
import java.util.ArrayList;

public class InsuranceProductDTOMapper {
    public PutInsuranceProductResponse toResponse(InsuranceProduct insuranceProduct){
        return new PutInsuranceProductResponse(
                insuranceProduct.id(),
                insuranceProduct.name(),
                insuranceProduct.insuranceType().name(),
                insuranceProduct.basePrice(),
                insuranceProduct.taxedPrice()
        );
    }

    public InsuranceProduct toInsuranceProduct(PutInsuranceProductRequest request){
        return new InsuranceProduct(
                new InsuranceType(new ArrayList<IInsuranceTax>(), request.categoria()),
                null,
                request.nome(),
                new BigDecimal(request.preco_base().toString()),
                request.preco_tarifado() != null ? new BigDecimal(request.preco_tarifado().toString()) : null
                );
    }
}
