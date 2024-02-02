package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes.InsuranceTypeEntityMapper;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalRepository;
import junit.framework.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InsuranceProductRepositoryTest {
    @Autowired
    private InsuranceProductRepository insuranceProductRepository;

    Pattern UUID_REGEX;
    UUID id;
    InsuranceProductEntityPK insuranceProductEntityPK;
    InsuranceProductEntity insuranceProductEntity;

    @BeforeEach
    public void init(){
        UUID_REGEX = Pattern
                .compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        id = UUID.randomUUID();
        insuranceProductEntityPK = new InsuranceProductEntityPK.InsuranceProductEntityPKBuilder()
                .setName("Seguro Auto Individual")
                .setCategory("AUTO")
                .build();
        insuranceProductEntity = new InsuranceProductEntity.InsuranceProductEntityBuilder()
                .setInsuranceProductEntityPK(insuranceProductEntityPK)
                .setId(id)
                .setBasePrice(BigDecimal.valueOf(1.0))
                .setTaxedPrice(BigDecimal.valueOf(1.5))
                .build();
    }

    @Test
    public void InsuranceProductRepository_Save_ReturnSaved(){
        //Arrange

        //Act
        InsuranceProductEntity savedInsuranceProductEntity = insuranceProductRepository.save(insuranceProductEntity);
        //Assert
        Assertions.assertThat(savedInsuranceProductEntity).isNotNull();
        Assertions.assertThat(UUID_REGEX.matcher(savedInsuranceProductEntity.getId().toString()).matches()).isTrue();
    }

    @Test
    public void InsuranceProductRepository_findByInsuranceProductEntityPK_ReturnInsuranceProductEntityPK(){
        //Arrange
        InsuranceProductEntity savedInsuranceProductEntity = insuranceProductRepository.save(insuranceProductEntity);
        //Act
        Optional<InsuranceProductEntity> foundInsuranceProductEntity = insuranceProductRepository.findByInsuranceProductEntityPK(insuranceProductEntityPK);
        //Assert
        Assertions.assertThat(foundInsuranceProductEntity.isPresent()).isTrue();
        Assertions.assertThat(savedInsuranceProductEntity).isEqualTo(foundInsuranceProductEntity.get());
    }
}