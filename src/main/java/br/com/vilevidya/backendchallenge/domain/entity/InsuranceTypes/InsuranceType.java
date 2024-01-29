package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTaxes.IInsuranceTax;

import java.util.List;

public record InsuranceType(List<IInsuranceTax> insuranceTaxList, String name) {
}
