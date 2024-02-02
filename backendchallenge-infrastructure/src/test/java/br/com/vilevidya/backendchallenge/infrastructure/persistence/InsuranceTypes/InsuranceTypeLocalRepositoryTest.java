package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InsuranceTypeLocalRepositoryTest {
    String insuranceTypeNotFoundExceptionMessage;
    String insuranceTypeName;
    String insuranceTypeNameNonExistent;
    @Autowired
    private InsuranceTypeLocalRepository insuranceTypeLocalRepository;

    @BeforeEach
    public void init(){
        insuranceTypeNotFoundExceptionMessage = "Categoria não encontrada com o nome: ";
        insuranceTypeName = "VIDA";
        insuranceTypeNameNonExistent = "BIDA";
    }

    @Test
    public void InsuranceTypeLocalRepository_FindByName_ReturnOptionalInsuranceTypeLocalEntityResult() throws InsuranceTypeNotFoundException {
        //Arrange

        //Act
        InsuranceTypeLocalEntity foundInsuranceTypeLocalEntity = insuranceTypeLocalRepository
                .findByName(insuranceTypeName);

        //Assert
        Assertions.assertThat(foundInsuranceTypeLocalEntity.getName()).isEqualTo(insuranceTypeName);

    }

    @Test
    public void InsuranceTypeLocalRepository_FindByName_ThrowsInsuranceTypeNotFoundException() {
        //Arrange

        //Act
        Throwable exception = catchThrowable(() -> {
            InsuranceTypeLocalEntity foundInsuranceTypeLocalEntity = insuranceTypeLocalRepository
                    .findByName(insuranceTypeNameNonExistent);
        });

        //Assert
        Assertions.assertThat(exception).isInstanceOf(InsuranceTypeNotFoundException.class)
                .hasMessage(insuranceTypeNotFoundExceptionMessage + insuranceTypeNameNonExistent);

    }

}