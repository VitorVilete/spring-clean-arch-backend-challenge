package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import org.assertj.core.api.Assertions;
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

    public static final String NAME = "Seguro Auto Individual";
    public static final String CATEGORY = "VIDA";
    public static final BigDecimal BASE_PRICE = BigDecimal.valueOf(1.0);
    public static final BigDecimal TAXED_PRICE = BigDecimal.valueOf(1.0);
    public static final UUID ID = UUID.randomUUID();
    public static final String VIDA = "VIDA";
    public static final BigDecimal IOF_TAX_VALUE = BigDecimal.valueOf(0.01);
    public static final BigDecimal PIS_TAX_VALUE = BigDecimal.valueOf(0.022);
    public static final BigDecimal COFINS_TAX_VALUE = BigDecimal.valueOf(0);
    @Mock
    InsuranceProductRepository insuranceProductRepository;

    @Mock
    InsuranceProductEntityMapper insuranceProductEntityMapper;
    @InjectMocks
    @Autowired
    InsuranceProductRepositoryGateway insuranceProductRepositoryGateway;

    private InsuranceProductEntity createInsuranceProductEntity(){
        return new InsuranceProductEntity.InsuranceProductEntityBuilder()
                .setInsuranceProductEntityPK(createInsuranceProductEntityPK())
                .setBasePrice(BASE_PRICE)
                .setTaxedPrice(TAXED_PRICE)
                .setId(ID)
                .build();
    }

    private InsuranceProductEntityPK createInsuranceProductEntityPK(){
        return new InsuranceProductEntityPK.InsuranceProductEntityPKBuilder()
                .setName(NAME)
                .setCategory(CATEGORY)
                .build();
    }

    private InsuranceProduct buildInsuranceProduct(){
        return new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(createInsuranceType())
                .setId(ID)
                .setName(NAME)
                .build();
    }
    private InsuranceType createInsuranceType(){
        return new InsuranceType.InsuranceTypeBuilder(VIDA, IOF_TAX_VALUE, PIS_TAX_VALUE, COFINS_TAX_VALUE).build();
    }

    @Test
    public void InsuranceProductRepositoryGateway_createInsuranceProduct_ReturnInsuranceProduct() {
        //Arrange
        when(insuranceProductRepository.findByInsuranceProductEntityPK(Mockito.any(InsuranceProductEntityPK.class)))
                .thenReturn(Optional.of(
                        createInsuranceProductEntity()
                        )
                );
        when(insuranceProductRepository.save(Mockito.any(InsuranceProductEntity.class)))
                .thenReturn(createInsuranceProductEntity());
        when(insuranceProductEntityMapper.toDomainObject(Mockito.any(InsuranceProductEntity.class)))
                .thenReturn(buildInsuranceProduct());
        when(insuranceProductEntityMapper.toEntity(Mockito.any(InsuranceProduct.class)))
                .thenReturn(createInsuranceProductEntity());
        InsuranceProduct insuranceProduct = buildInsuranceProduct();
        //Act
        InsuranceProduct resultInsuranceProduct = insuranceProductRepositoryGateway.createInsuranceProduct(insuranceProduct);

        //Assert
        Assertions.assertThat(resultInsuranceProduct.getId()).isEqualTo(insuranceProduct.getId());
    }

}