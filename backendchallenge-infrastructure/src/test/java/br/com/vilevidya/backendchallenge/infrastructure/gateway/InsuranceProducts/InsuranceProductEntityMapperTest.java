package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
class InsuranceProductEntityMapperTest {

    public static final String NAME = "test";
    public static final UUID ID = UUID.randomUUID();
    public static final double BASE_PRICE_DOUBLE = BigDecimal.valueOf(100.00).doubleValue();
    public static final BigDecimal BASE_PRICE = BigDecimal.valueOf(100.00);
    public static final double TAXED_PRICE_DOUBLE = BigDecimal.valueOf(101.00).doubleValue();
    public static final BigDecimal TAXED_PRICE = BigDecimal.valueOf(101.00);
    @Autowired
    private InsuranceProductEntityMapper insuranceProductEntityMapper;

    private InsuranceProduct buildInsuranceProduct(){
        return new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(new InsuranceType.InsuranceTypeBuilder()
                        .setCofinsTaxValue(0)
                        .setIofTaxValue(0)
                        .setPisTaxValue(0)
                        .build())
                .setId(ID)
                .setName(NAME)
                .setBasePrice(BASE_PRICE_DOUBLE)
                .setTaxedPrice(TAXED_PRICE_DOUBLE)
                .build();
    }

    private InsuranceProductEntity buildInsuranceProductEntity(){
        return new InsuranceProductEntity.InsuranceProductEntityBuilder()
                .setId(ID)
                .setInsuranceProductEntityPK(new InsuranceProductEntityPK.InsuranceProductEntityPKBuilder()
                        .setName(NAME)
                        .build())
                .setBasePrice(BASE_PRICE)
                .setTaxedPrice(TAXED_PRICE)
                .build();
    }

    @Test
    public void InsuranceProductEntityMapper_toEntity_ReturnInsuranceProductEntity(){
        //Arrange
        InsuranceProduct insuranceProductDomainObject = buildInsuranceProduct();
        InsuranceProductEntity insuranceProductEntity = buildInsuranceProductEntity();

        //Act
        InsuranceProductEntity mappedInsuranceProductEntity = insuranceProductEntityMapper.toEntity(insuranceProductDomainObject);

        //Assert
        Assertions.assertThat(mappedInsuranceProductEntity).usingRecursiveComparison().isEqualTo(insuranceProductEntity);
    }

    @Test
    public void InsuranceProductEntityMapper_toDomainObject_ReturnInsuranceProduct(){
        //Arrange
        InsuranceProduct insuranceProductDomainObject = buildInsuranceProduct();
        InsuranceProductEntity insuranceProductEntity = buildInsuranceProductEntity();

        //Act
        InsuranceProduct mappedInsuranceProductDomainObject = insuranceProductEntityMapper.toDomainObject(insuranceProductEntity);

        //Assert
        Assertions.assertThat(mappedInsuranceProductDomainObject).usingRecursiveComparison().isEqualTo(insuranceProductDomainObject);
    }

}