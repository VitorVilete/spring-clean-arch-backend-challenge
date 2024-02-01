package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InsuranceTypeLocalRepositoryTest {
    @Autowired
    private InsuranceTypeLocalRepository insuranceTypeLocalRepository;

    @Test
    public void InsuranceTypeLocalRepository_FindByName_ReturnOptionalInsuranceTypeLocalEntityResult(){
        //Arrange
        String insuranceTypeName = "VIDA";

        //Act
        Optional<InsuranceTypeLocalEntity> foundInsuranceTypeLocalEntity = insuranceTypeLocalRepository.findByName(insuranceTypeName);

        //Assert
        Assertions.assertThat(foundInsuranceTypeLocalEntity.isPresent()).isTrue();
        Assertions.assertThat(foundInsuranceTypeLocalEntity.get().getName()).isEqualTo(insuranceTypeName);

    }

    @Test
    public void InsuranceTypeLocalRepository_FindByName_ReturnOptionalInsuranceTypeLocalEntityResultNull(){
        //Arrange
        String insuranceTypeName = "BIDA";

        //Act
        Optional<InsuranceTypeLocalEntity> foundInsuranceTypeLocalEntity = insuranceTypeLocalRepository.findByName(insuranceTypeName);

        //Assert
        Assertions.assertThat(foundInsuranceTypeLocalEntity.isEmpty()).isTrue();

    }

}