package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InsuranceTypeLocalRepositoryTest {
    public static final String CATEGORIA_NAO_ENCONTRADA_COM_O_NOME = "Categoria não encontrada com o nome: ";
    @Autowired
    private InsuranceTypeLocalRepository insuranceTypeLocalRepository;

    @Test
    public void InsuranceTypeLocalRepository_FindByName_ReturnOptionalInsuranceTypeLocalEntityResult() throws InsuranceTypeNotFoundException {
        //Arrange
        String insuranceTypeName = "VIDA";

        //Act
        InsuranceTypeLocalEntity foundInsuranceTypeLocalEntity = insuranceTypeLocalRepository.findByName(insuranceTypeName);

        //Assert
        Assertions.assertThat(foundInsuranceTypeLocalEntity.getName()).isEqualTo(insuranceTypeName);

    }

    @Test
    public void InsuranceTypeLocalRepository_FindByName_ThrowsInsuranceTypeNotFoundException() throws InsuranceTypeNotFoundException {
        //Arrange
        String insuranceTypeName = "BIDA";

        //Act
        Throwable exception = catchThrowable(() -> {
            InsuranceTypeLocalEntity foundInsuranceTypeLocalEntity = insuranceTypeLocalRepository.findByName(insuranceTypeName);
        });

        //Assert
        Assertions.assertThat(exception).isInstanceOf(InsuranceTypeNotFoundException.class).hasMessage(CATEGORIA_NAO_ENCONTRADA_COM_O_NOME + insuranceTypeName);

    }

}