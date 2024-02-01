package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class InsuranceTypeLocalRepository {
    static final ArrayList<InsuranceTypeLocalEntity> insuranceTypeEntities = new ArrayList<>(
            Arrays.asList(
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("VIDA", BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.022), BigDecimal.valueOf(0)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("AUTO", BigDecimal.valueOf(0.055), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("VIAGEM", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("RESIDENCIAL", BigDecimal.valueOf(0.04), BigDecimal.valueOf(0), BigDecimal.valueOf(0.03)).build(),
                    new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder("PATRIMONIAL", BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.03), BigDecimal.valueOf(0)).build()
            )
    );
    public InsuranceTypeLocalEntity findByName(String insuranceTypeName){
        return insuranceTypeEntities.stream().filter(insuranceType -> insuranceTypeName.equals(insuranceType.getName())).findAny().orElse(null);
    }
}
