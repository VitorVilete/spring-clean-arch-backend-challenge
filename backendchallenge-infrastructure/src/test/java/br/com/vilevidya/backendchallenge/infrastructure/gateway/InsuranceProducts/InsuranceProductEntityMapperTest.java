package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
class InsuranceProductEntityMapperTest {

    @Autowired
    private InsuranceProductEntityMapper insuranceProductEntityMapper;
    String insuranceProductName;
    String insuranceProductCategory;
    BigDecimal insuranceProductBasePrice;
    BigDecimal insuranceProductTaxedPrice;
    UUID insuranceProductId;
    String insuranceTypeName;
    BigDecimal insuranceTypeIofTaxValue;
    BigDecimal insuranceTypePisTaxValue;
    BigDecimal insuranceTypeCofinsTaxValue;
    InsuranceProductEntity insuranceProductEntity;
    InsuranceProductEntityPK insuranceProductEntityPK;
    InsuranceProduct insuranceProductDomainObject;
    InsuranceType insuranceType;

    @BeforeEach
    public void init(){
        insuranceProductBasePrice = BigDecimal.valueOf(1.0);
        insuranceProductTaxedPrice = BigDecimal.valueOf(1.0);
        insuranceProductId = UUID.randomUUID();
        insuranceTypeName = "VIDA";
        insuranceProductCategory = insuranceTypeName;
        insuranceTypeIofTaxValue = BigDecimal.valueOf(0.01);
        insuranceTypePisTaxValue = BigDecimal.valueOf(0.022);
        insuranceTypeCofinsTaxValue = BigDecimal.valueOf(0);
        insuranceProductEntityPK = new InsuranceProductEntityPK.InsuranceProductEntityPKBuilder()
                .setName(insuranceProductName)
                .setCategory(insuranceProductCategory)
                .build();
        insuranceProductEntity = new InsuranceProductEntity.InsuranceProductEntityBuilder()
                .setInsuranceProductEntityPK(insuranceProductEntityPK)
                .setBasePrice(insuranceProductBasePrice)
                .setTaxedPrice(insuranceProductTaxedPrice)
                .setId(insuranceProductId)
                .build();
        insuranceType = new InsuranceType
                .InsuranceTypeBuilder().setName(insuranceTypeName).build();
        insuranceProductDomainObject = new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(insuranceType)
                .setId(insuranceProductId)
                .setName(insuranceProductName)
                .setBasePrice(insuranceProductBasePrice.doubleValue())
                .setTaxedPrice(insuranceProductTaxedPrice.doubleValue())
                .build();
    }

    @Test
    public void InsuranceProductEntityMapper_toEntity_ReturnInsuranceProductEntity(){
        //Arrange

        //Act
        InsuranceProductEntity mappedInsuranceProductEntity = insuranceProductEntityMapper.toEntity(insuranceProductDomainObject);

        //Assert
        Assertions.assertThat(mappedInsuranceProductEntity).usingRecursiveComparison().isEqualTo(insuranceProductEntity);
    }

    @Test
    public void InsuranceProductEntityMapper_toDomainObject_ReturnInsuranceProduct(){
        //Arrange

        //Act
        InsuranceProduct mappedInsuranceProductDomainObject = insuranceProductEntityMapper.toDomainObject(insuranceProductEntity);

        //Assert
        Assertions.assertThat(mappedInsuranceProductDomainObject).usingRecursiveComparison().isEqualTo(insuranceProductDomainObject);
    }

}