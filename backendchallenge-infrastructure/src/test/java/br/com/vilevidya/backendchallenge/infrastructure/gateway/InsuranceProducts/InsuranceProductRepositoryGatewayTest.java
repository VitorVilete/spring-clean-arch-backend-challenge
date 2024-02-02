package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsuranceProductRepositoryGatewayTest {
    @Mock
    InsuranceProductRepository insuranceProductRepository;

    @Mock
    InsuranceProductEntityMapper insuranceProductEntityMapper;
    @InjectMocks
    @Autowired
    InsuranceProductRepositoryGateway insuranceProductRepositoryGateway;

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
    InsuranceProduct insuranceProduct;
    InsuranceType insuranceType;

    @BeforeEach
    public void init(){
        insuranceProductBasePrice = BigDecimal.valueOf(1.0);
        insuranceProductTaxedPrice = BigDecimal.valueOf(1.0);
        insuranceProductId = UUID.randomUUID();
        insuranceTypeName = "VIDA";
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
                .InsuranceTypeBuilder(insuranceTypeName, insuranceTypeIofTaxValue, insuranceTypePisTaxValue, insuranceTypeCofinsTaxValue).build();
        insuranceProduct = new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(insuranceType)
                .setId(insuranceProductId)
                .setName(insuranceProductName)
                .build();
    }

    @Test
    public void InsuranceProductRepositoryGateway_createInsuranceProduct_ReturnInsuranceProduct() {
        //Arrange
        when(insuranceProductRepository.findByInsuranceProductEntityPK(Mockito.any(InsuranceProductEntityPK.class)))
                .thenReturn(Optional.of(insuranceProductEntity)
                );
        when(insuranceProductRepository.save(Mockito.any(InsuranceProductEntity.class)))
                .thenReturn(insuranceProductEntity);
        when(insuranceProductEntityMapper.toDomainObject(Mockito.any(InsuranceProductEntity.class)))
                .thenReturn(insuranceProduct);
        when(insuranceProductEntityMapper.toEntity(Mockito.any(InsuranceProduct.class)))
                .thenReturn(insuranceProductEntity);
        //Act
        InsuranceProduct resultInsuranceProduct = insuranceProductRepositoryGateway.createInsuranceProduct(insuranceProduct);

        //Assert
        Assertions.assertThat(resultInsuranceProduct.getId()).isEqualTo(insuranceProduct.getId());
    }

}