package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class InsuranceTypeEntityMapperTest {
    @Autowired
    private InsuranceTypeEntityMapper insuranceTypeEntityMapper;

    String insuranceTypeName;
    BigDecimal insuranceTypeIofTaxValue;
    BigDecimal insuranceTypePisTaxValue;
    BigDecimal insuranceTypeCofinsTaxValue;

    InsuranceType insuranceTypeDomainObject;
    InsuranceTypeLocalEntity insuranceTypeLocalEntity;

    @BeforeEach
    public void init(){
        insuranceTypeName = "test";
        insuranceTypeIofTaxValue = BigDecimal.valueOf(1.0);
        insuranceTypePisTaxValue = BigDecimal.valueOf(2.0);
        insuranceTypeCofinsTaxValue = BigDecimal.valueOf(3.0);
        insuranceTypeDomainObject = new InsuranceType.InsuranceTypeBuilder()
                .setName(insuranceTypeName)
                .setIofTaxValue(insuranceTypeIofTaxValue)
                .setPisTaxValue(insuranceTypePisTaxValue)
                .setCofinsTaxValue(insuranceTypeCofinsTaxValue)
                .build();
        insuranceTypeLocalEntity = new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder()
                .setName(insuranceTypeName)
                .setIofTaxValue(insuranceTypeIofTaxValue)
                .setPisTaxValue(insuranceTypePisTaxValue)
                .setCofinsTaxValue(insuranceTypeCofinsTaxValue)
                .build();
    }

    @Test
    public void InsuranceTypeEntityMapper_toLocalEntity_ReturnInsuranceTypeLocalEntity(){
        //Arrange

        //Act
        InsuranceTypeLocalEntity mappedInsuranceTypeLocalEntity = insuranceTypeEntityMapper.toLocalEntity(insuranceTypeDomainObject);

        //Assert
        Assertions.assertThat(mappedInsuranceTypeLocalEntity).usingRecursiveComparison().isEqualTo(insuranceTypeLocalEntity);
    }

    @Test
    public void InsuranceTypeEntityMapper_toDomainObject_ReturnInsuranceType(){
        //Arrange

        //Act
        InsuranceType mappedInsuranceTypeDomainObject = insuranceTypeEntityMapper.toDomainObject(insuranceTypeLocalEntity);

        //Assert
        Assertions.assertThat(mappedInsuranceTypeDomainObject).usingRecursiveComparison().isEqualTo(insuranceTypeDomainObject);
    }
}