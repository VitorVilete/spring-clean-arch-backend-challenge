package br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindInsuranceTypeByNameUseCaseTest {

    public static final String VIDA = "VIDA";
    public static final BigDecimal IOF_TAX_VALUE = BigDecimal.valueOf(0.01);
    public static final BigDecimal PIS_TAX_VALUE = BigDecimal.valueOf(0.022);
    public static final BigDecimal COFINS_TAX_VALUE = BigDecimal.valueOf(0);
    public static final String CATEGORIA_NAO_ENCONTRADA_COM_O_NOME_VIDA = "Categoria não encontrada com o nome: VIDA";

    @Mock
    IInsuranceTypeGateway iInsuranceTypeGateway;

    @Autowired
    @InjectMocks
    FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;

    private InsuranceType createInsuranceType(){
        return new InsuranceType.InsuranceTypeBuilder(VIDA, IOF_TAX_VALUE, PIS_TAX_VALUE, COFINS_TAX_VALUE).build();
    }

    @Test
    public void FindInsuranceTypeByNameUseCase_findInsuranceTypeByName_returnInsuranceType() throws InsuranceTypeNotFoundException {
        //Arrange
        when(iInsuranceTypeGateway.findInsuranceTypeByName(Mockito.any(String.class)))
                .thenReturn(createInsuranceType());
        //Act
        InsuranceType resultInsuranceType = findInsuranceTypeByNameUseCase.findInsuranceTypeByName(VIDA);
        //Assert
        Assertions.assertThat(resultInsuranceType).isNotNull();


    }

    @Test
    public void FindInsuranceTypeByNameUseCase_findInsuranceTypeByName_ThrowInsuranceTypeNotFoundException() throws InsuranceTypeNotFoundException {
        //Arrange
        when(iInsuranceTypeGateway.findInsuranceTypeByName(Mockito.any(String.class)))
                .thenThrow(new InsuranceTypeNotFoundException(CATEGORIA_NAO_ENCONTRADA_COM_O_NOME_VIDA));

        //Act
        Throwable exception = catchThrowable(() -> {
            InsuranceType resultInsuranceType = findInsuranceTypeByNameUseCase.findInsuranceTypeByName(VIDA);
        });

        //Assert
        Assertions.assertThat(exception).isInstanceOf(InsuranceTypeNotFoundException.class).hasMessage(CATEGORIA_NAO_ENCONTRADA_COM_O_NOME_VIDA);
    }

}