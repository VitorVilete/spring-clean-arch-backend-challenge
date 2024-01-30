package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes;

import java.math.BigDecimal;
import java.util.List;

public record InsuranceType(String name, BigDecimal iofTaxValue, BigDecimal pisTaxValue, BigDecimal cofinsTaxValue) {
}
