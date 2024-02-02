package br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
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

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindInsuranceTypeByNameUseCaseTest {

    @Mock
    IInsuranceTypeGateway iInsuranceTypeGateway;

    @Autowired
    @InjectMocks
    FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;

    private InsuranceType insuranceType;
    private String insuranceTypeName;
    private BigDecimal insuranceTypeIofTaxValue;
    private BigDecimal insuranceTypePisTaxValue;
    private BigDecimal insuranceTypeCofinsTaxValue;
    private String insuranceTypeNotFoundExceptionMessage;

    @BeforeEach
    public void init(){
        insuranceType = new InsuranceType.InsuranceTypeBuilder(insuranceTypeName, insuranceTypeIofTaxValue, insuranceTypePisTaxValue, insuranceTypeCofinsTaxValue).build();
        insuranceTypeName = "VIDA";
        insuranceTypeIofTaxValue = BigDecimal.valueOf(0.01);
        insuranceTypePisTaxValue = BigDecimal.valueOf(0.022);
        insuranceTypeCofinsTaxValue = BigDecimal.valueOf(0);
        insuranceTypeNotFoundExceptionMessage = "Categoria não encontrada com o nome: VIDA";
    }

    @Test
    public void FindInsuranceTypeByNameUseCase_findInsuranceTypeByName_returnInsuranceType() throws InsuranceTypeNotFoundException {
        //Arrange
        when(iInsuranceTypeGateway.findInsuranceTypeByName(Mockito.any(String.class)))
                .thenReturn(insuranceType);
        //Act
        InsuranceType resultInsuranceType = findInsuranceTypeByNameUseCase.findInsuranceTypeByName(insuranceTypeName);
        //Assert
        Assertions.assertThat(resultInsuranceType).isNotNull();
    }

    @Test
    public void FindInsuranceTypeByNameUseCase_findInsuranceTypeByName_ThrowInsuranceTypeNotFoundException() throws InsuranceTypeNotFoundException {
        //Arrange
        when(iInsuranceTypeGateway.findInsuranceTypeByName(Mockito.any(String.class)))
                .thenThrow(new InsuranceTypeNotFoundException(insuranceTypeNotFoundExceptionMessage));

        //Act
        Throwable exception = catchThrowable(() -> {
            findInsuranceTypeByNameUseCase.findInsuranceTypeByName(insuranceTypeName);
        });

        //Assert
        Assertions.assertThat(exception).isInstanceOf(InsuranceTypeNotFoundException.class).hasMessage(insuranceTypeNotFoundExceptionMessage);
    }

}