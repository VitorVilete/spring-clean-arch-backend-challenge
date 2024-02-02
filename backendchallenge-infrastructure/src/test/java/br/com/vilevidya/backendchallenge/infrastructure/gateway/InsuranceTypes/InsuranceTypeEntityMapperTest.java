package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class InsuranceTypeEntityMapperTest {

    private static final BigDecimal IOF_TAX_VALUE = BigDecimal.valueOf(1.0);
    private static final BigDecimal PIS_TAX_VALUE = BigDecimal.valueOf(2.0);
    private static final BigDecimal COFINS_TAX_VALUE = BigDecimal.valueOf(3.0);
    @Autowired
    private InsuranceTypeEntityMapper insuranceTypeEntityMapper;

    private InsuranceType buildInsuranceType(){
        return new InsuranceType.InsuranceTypeBuilder()
                .setName("test")
                .setIofTaxValue(IOF_TAX_VALUE)
                .setPisTaxValue(PIS_TAX_VALUE)
                .setCofinsTaxValue(COFINS_TAX_VALUE)
                .build();
    }

    private InsuranceTypeLocalEntity buildInsuranceTypeLocalEntity(){
        return new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder()
                .setName("test")
                .setIofTaxValue(IOF_TAX_VALUE)
                .setPisTaxValue(PIS_TAX_VALUE)
                .setCofinsTaxValue(COFINS_TAX_VALUE)
                .build();
    }

    @Test
    public void InsuranceTypeEntityMapper_toLocalEntity_ReturnInsuranceTypeLocalEntity(){
        //Arrange
        InsuranceType insuranceTypeDomainObject = buildInsuranceType();
        InsuranceTypeLocalEntity insuranceTypeLocalEntity = buildInsuranceTypeLocalEntity();

        //Act
        InsuranceTypeLocalEntity mappedInsuranceTypeLocalEntity = insuranceTypeEntityMapper.toLocalEntity(insuranceTypeDomainObject);

        //Assert
        Assertions.assertThat(mappedInsuranceTypeLocalEntity).usingRecursiveComparison().isEqualTo(insuranceTypeLocalEntity);
    }

    @Test
    public void InsuranceTypeEntityMapper_toDomainObject_ReturnInsuranceType(){
        //Arrange
        InsuranceType insuranceTypeDomainObject = buildInsuranceType();
        InsuranceTypeLocalEntity insuranceTypeLocalEntity = buildInsuranceTypeLocalEntity();

        //Act
        InsuranceType mappedInsuranceTypeDomainObject = insuranceTypeEntityMapper.toDomainObject(insuranceTypeLocalEntity);

        //Assert
        Assertions.assertThat(mappedInsuranceTypeDomainObject).usingRecursiveComparison().isEqualTo(insuranceTypeDomainObject);
    }
}