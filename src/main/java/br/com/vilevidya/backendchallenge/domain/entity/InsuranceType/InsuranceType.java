package br.com.vilevidya.backendchallenge.domain.entity.InsuranceType;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTax.IInsuranceTax;

import java.util.Set;

public record InsuranceType(Set<IInsuranceTax> insuranceTaxList, String name) {
}
