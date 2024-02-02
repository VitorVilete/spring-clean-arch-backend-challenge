package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsuranceTypeRepositoryGatewayTest {

    public static final String VIDA = "VIDA";
    public static final BigDecimal IOF_TAX_VALUE = BigDecimal.valueOf(0.01);
    public static final BigDecimal PIS_TAX_VALUE = BigDecimal.valueOf(0.022);
    public static final BigDecimal COFINS_TAX_VALUE = BigDecimal.valueOf(0);
    public static final String CATEGORIA_NAO_ENCONTRADA_COM_O_NOME_VIDA = "Categoria não encontrada com o nome: VIDA";
    @Mock
    InsuranceTypeLocalRepository insuranceTypeLocalRepository;

    @Mock
    InsuranceTypeEntityMapper insuranceTypeEntityMapper;
    @InjectMocks
    @Autowired
    InsuranceTypeRepositoryGateway insuranceTypeRepositoryGateway;

    private InsuranceTypeLocalEntity createInsuranceTypeLocalEntity(){
        return new InsuranceTypeLocalEntity.InsuranceTypeLocalEntityBuilder(VIDA, IOF_TAX_VALUE, PIS_TAX_VALUE, COFINS_TAX_VALUE).build();
    }
    @Test
    public void InsuranceTypeRepositoryGateway_findInsuranceTypeByName_ReturnInsuranceTypeLocalEntity() throws InsuranceTypeNotFoundException {
        //Arrange
        when(insuranceTypeLocalRepository.findByName(Mockito.any(String.class)))
                .thenReturn(createInsuranceTypeLocalEntity());
        when(insuranceTypeEntityMapper.toDomainObject(Mockito.any(InsuranceTypeLocalEntity.class)))
                .thenReturn(new InsuranceType.InsuranceTypeBuilder().build());

        //Act
        InsuranceType resultInsuranceType = insuranceTypeRepositoryGateway.findInsuranceTypeByName(VIDA);

        //Assert
        Assertions.assertThat(resultInsuranceType).isNotNull();
    }

}