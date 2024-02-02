package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class InsuranceTypeLocalRepository {
    public static final ArrayList<InsuranceTypeLocalEntity> insuranceTypeEntities = new ArrayList<>(
            Arrays.asList(
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("VIDA", BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.022), BigDecimal.valueOf(0)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("AUTO", BigDecimal.valueOf(0.055), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("VIAGEM", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("RESIDENCIAL", BigDecimal.valueOf(0.04), BigDecimal.valueOf(0), BigDecimal.valueOf(0.03)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("PATRIMONIAL", BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.03), BigDecimal.valueOf(0)).build()
            )
    );
    public InsuranceTypeLocalEntity findByName(String insuranceTypeName) throws InsuranceTypeNotFoundException {
        return insuranceTypeEntities.stream()
                .filter(insuranceType -> insuranceTypeName.equals(insuranceType.getName()))
                .findAny()
                .orElseThrow(() -> new InsuranceTypeNotFoundException("Categoria não encontrada com o nome: "+ insuranceTypeName));
    }
}
