package br.com.vilevidya.backendchallenge.infrastructure.config.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts.InsuranceProductEntityMapper;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts.InsuranceProductRepositoryGateway;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes.InsuranceTypeEntityMapper;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceProductConfig {
    @Bean
    CreateInsuranceProductUseCase createInsuranceProduct(IInsuranceProductGateway insuranceProductGateway){
        return new CreateInsuranceProductUseCase(insuranceProductGateway);
    }

    @Bean
    IInsuranceProductGateway insuranceProductGateway(InsuranceProductRepository insuranceProductRepository, InsuranceProductEntityMapper insuranceProductEntityMapper){
        return new InsuranceProductRepositoryGateway(insuranceProductRepository, insuranceProductEntityMapper);
    }

    @Bean
    InsuranceProductEntityMapper insuranceProductEntityMapper(){
        return new InsuranceProductEntityMapper();
    }

    @Bean
    InsuranceProductDTOMapper insuranceProductDTOMapper(){
        return new InsuranceProductDTOMapper();
    }
}
