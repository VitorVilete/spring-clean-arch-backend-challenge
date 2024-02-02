package br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
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
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateInsuranceProductUseCaseTest {


    @Mock
    IInsuranceProductGateway IInsuranceProductGateway;

    @Autowired
    @InjectMocks
    CreateInsuranceProductUseCase createInsuranceProductUseCase;

    String insuranceProductName;
    UUID insuranceProductId;
    double insuranceProductBasePrice;
    double insuranceProductTaxedPrice;

    InsuranceType insuranceType;
    InsuranceProduct insuranceProduct;
    InsuranceProduct insuranceProductWithoutTaxes;

    private double calculateTaxes(InsuranceProduct insuranceProduct){
        double result;
        if (insuranceProduct.getTaxedPrice() == 0){
            double basePrice = insuranceProduct.getBasePrice();
            InsuranceType insuranceType = insuranceProduct.getInsuranceType();

            result = basePrice +
                    (basePrice * insuranceType.iofTaxValue().doubleValue()) +
                    (basePrice * insuranceType.pisTaxValue().doubleValue()) +
                    (basePrice * insuranceType.cofinsTaxValue().doubleValue());
        }else{
            result = insuranceProduct.getTaxedPrice();
        }
        return result;
    }

    @BeforeEach
    public void init(){
        insuranceProductName = "test";
        insuranceProductId = UUID.randomUUID();
        insuranceProductBasePrice = BigDecimal.valueOf(100.00).doubleValue();
        insuranceProductTaxedPrice = BigDecimal.valueOf(101.00).doubleValue();
        insuranceType = new InsuranceType.InsuranceTypeBuilder()
                .setCofinsTaxValue(0)
                .setIofTaxValue(0)
                .setPisTaxValue(0)
                .build();
        insuranceProduct = new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(insuranceType)
                .setId(insuranceProductId)
                .setName(insuranceProductName)
                .setBasePrice(insuranceProductBasePrice)
                .setTaxedPrice(insuranceProductTaxedPrice)
                .build();
        insuranceProductWithoutTaxes = new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(insuranceType)
                .setId(insuranceProductId)
                .setName(insuranceProductName)
                .setBasePrice(insuranceProductBasePrice)
                .setTaxedPrice(0)
                .build();
    }

    @Test
    public void CreateInsuranceProductUseCaseTest_createInsuranceProduct_shouldCallIInsuranceProductGatewayOnce(){
        //Arrange
        when(IInsuranceProductGateway.createInsuranceProduct(Mockito.any(InsuranceProduct.class)))
                .thenReturn(insuranceProduct);

        //Act
        InsuranceProduct result = createInsuranceProductUseCase.createInsuranceProduct(insuranceProduct);

        //Assert
        verify(IInsuranceProductGateway, times(1)).createInsuranceProduct(Mockito.any(InsuranceProduct.class));
    }

    @Test
    public void CreateInsuranceProductUseCaseTest_calculateTaxes_shouldCalculateTaxesCorrectly(){
        //Arrange
        InsuranceProduct insuranceProductAlreadyTaxed = insuranceProduct;
        InsuranceProduct insuranceProductWithoutTax = insuranceProductWithoutTaxes;

        //Act
        insuranceProductAlreadyTaxed.setTaxedPrice(createInsuranceProductUseCase.calculateTaxes(insuranceProductAlreadyTaxed));
        double calculatedTaxesAlreadyTaxed = calculateTaxes(insuranceProductAlreadyTaxed);
        insuranceProductWithoutTax.setTaxedPrice(createInsuranceProductUseCase.calculateTaxes(insuranceProductWithoutTax));
        double calculatedTaxesNoTaxes = calculateTaxes(insuranceProductWithoutTax);
        //Assert
        Assertions.assertThat(insuranceProductWithoutTax.getTaxedPrice()).isEqualTo(calculatedTaxesNoTaxes);
        Assertions.assertThat(insuranceProductAlreadyTaxed.getTaxedPrice()).isEqualTo(calculatedTaxesAlreadyTaxed);



    }

}