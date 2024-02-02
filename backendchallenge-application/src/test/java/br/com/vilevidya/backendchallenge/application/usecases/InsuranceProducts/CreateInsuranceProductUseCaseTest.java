package br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;
import org.assertj.core.api.Assertions;
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

    public static final String NAME = "test";
    public static final UUID ID = UUID.randomUUID();
    public static final double BASE_PRICE_DOUBLE = BigDecimal.valueOf(100.00).doubleValue();
    public static final double TAXED_PRICE_DOUBLE = BigDecimal.valueOf(101.00).doubleValue();

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
    private InsuranceProduct buildInsuranceWithoutTax(){
        return new InsuranceProduct.InsuranceProductBuilder()
                .setInsuranceType(new InsuranceType.InsuranceTypeBuilder()
                        .setCofinsTaxValue(0)
                        .setIofTaxValue(0)
                        .setPisTaxValue(0)
                        .build())
                .setId(ID)
                .setName(NAME)
                .setBasePrice(BASE_PRICE_DOUBLE)
                .setTaxedPrice(0)
                .build();
    }

    @Test
    public void CreateInsuranceProductUseCaseTest_createInsuranceProduct_shouldCallIInsuranceProductGatewayOnce(){
        //Arrange
        when(IInsuranceProductGateway.createInsuranceProduct(Mockito.any(InsuranceProduct.class)))
                .thenReturn(buildInsuranceProduct());

        //Act
        InsuranceProduct result = createInsuranceProductUseCase.createInsuranceProduct(buildInsuranceProduct());

        //Assert
        verify(IInsuranceProductGateway, times(1)).createInsuranceProduct(Mockito.any(InsuranceProduct.class));
    }

    @Test
    public void CreateInsuranceProductUseCaseTest_calculateTaxes_shouldCalculateTaxesCorrectly(){
        //Arrange
        InsuranceProduct insuranceProductAlreadyTaxed = buildInsuranceProduct();
        InsuranceProduct insuranceProductWithoutTax = buildInsuranceWithoutTax();

        //Act
        insuranceProductAlreadyTaxed.setTaxedPrice(createInsuranceProductUseCase.calculateTaxes(insuranceProductAlreadyTaxed));
        double calculatedTaxesAlreadyTaxed = calculateTaxes(insuranceProductAlreadyTaxed);
        insuranceProductWithoutTax.setTaxedPrice(createInsuranceProductUseCase.calculateTaxes(insuranceProductWithoutTax));
        double calculatedTaxesNoTaxes = calculateTaxes(insuranceProductWithoutTax);
        //Assert
        Assertions.assertThat(insuranceProductWithoutTax.getTaxedPrice()).isEqualTo(calculatedTaxesNoTaxes);
        Assertions.assertThat(insuranceProductAlreadyTaxed.getTaxedPrice()).isEqualTo(calculatedTaxesAlreadyTaxed);



    }
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

}