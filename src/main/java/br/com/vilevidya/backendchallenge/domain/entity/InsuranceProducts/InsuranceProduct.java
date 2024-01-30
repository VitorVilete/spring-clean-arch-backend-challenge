package br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.math.BigDecimal;

public record InsuranceProduct(InsuranceType insuranceType, String name, BigDecimal basePrice, BigDecimal taxedPrice) {
}
