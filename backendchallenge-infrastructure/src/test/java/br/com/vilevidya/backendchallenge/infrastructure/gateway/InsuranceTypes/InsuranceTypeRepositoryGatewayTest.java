package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsuranceTypeRepositoryGatewayTest {

    String insuranceTypeLocalEntityName;
    BigDecimal insuranceTypeLocalEntityIofTaxValue;
    BigDecimal insuranceTypeLocalEntityPisTaxValue;
    BigDecimal insuranceTypeLocalEntityCofinsTaxValue;
    @Mock
    InsuranceTypeLocalRepository insuranceTypeLocalRepository;

    @Mock
    InsuranceTypeEntityMapper insuranceTypeEntityMapper;
    @InjectMocks
    @Autowired
    InsuranceTypeRepositoryGateway insuranceTypeRepositoryGateway;

    InsuranceTypeLocalEntity insuranceTypeLocalEntity;

    @BeforeEach
    public void init(){
        insuranceTypeLocalEntityName = "VIDA";
        insuranceTypeLocalEntityIofTaxValue = BigDecimal.valueOf(0.01);
        insuranceTypeLocalEntityPisTaxValue = BigDecimal.valueOf(0.022);
        insuranceTypeLocalEntityCofinsTaxValue = BigDecimal.valueOf(0);
        insuranceTypeLocalEntity = new InsuranceTypeLocalEntity
                .InsuranceTypeLocalEntityBuilder(insuranceTypeLocalEntityName, insuranceTypeLocalEntityIofTaxValue, insuranceTypeLocalEntityPisTaxValue, insuranceTypeLocalEntityCofinsTaxValue).build();
    }

    @Test
    public void InsuranceTypeRepositoryGateway_findInsuranceTypeByName_ReturnInsuranceTypeLocalEntity() throws InsuranceTypeNotFoundException {
        //Arrange
        when(insuranceTypeLocalRepository.findByName(Mockito.any(String.class)))
                .thenReturn(insuranceTypeLocalEntity);
        when(insuranceTypeEntityMapper.toDomainObject(Mockito.any(InsuranceTypeLocalEntity.class)))
                .thenReturn(new InsuranceType.InsuranceTypeBuilder().build());

        //Act
        InsuranceType resultInsuranceType = insuranceTypeRepositoryGateway.findInsuranceTypeByName(insuranceTypeLocalEntityName);

        //Assert
        Assertions.assertThat(resultInsuranceType).isNotNull();
    }

}