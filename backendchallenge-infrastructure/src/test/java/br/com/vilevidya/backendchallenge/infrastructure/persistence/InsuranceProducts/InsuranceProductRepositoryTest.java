package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InsuranceProductRepositoryTest {
    @Autowired
    private InsuranceProductRepository insuranceProductRepository;

    @Test
    public void InsuranceProductRepository_Save_ReturnSaved(){
        //Arrange
//        InsuranceProductEntity insuranceProductEntity

        //Act

        //Assert
    }
}