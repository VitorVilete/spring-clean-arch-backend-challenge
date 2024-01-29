package br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceType.InsuranceType;

import java.util.List;

public record InsuranceProduct(InsuranceType insuranceType, String name, float basePrice, float taxedPrice) {
}
