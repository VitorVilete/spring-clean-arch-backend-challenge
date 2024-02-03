package br.com.vilevidya.backendchallenge.infrastructure.config.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.InsuranceProductDTOMapper;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeByNameUseCase;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts.InsuranceProductEntityMapper;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts.InsuranceProductRepositoryGateway;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceProductConfig {
    @Bean
    CreateInsuranceProductUseCase createInsuranceProduct(IInsuranceProductGateway insuranceProductGateway, InsuranceProductDTOMapper insuranceProductDTOMapper, FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase){
        return new CreateInsuranceProductUseCase(insuranceProductGateway, insuranceProductDTOMapper, findInsuranceTypeByNameUseCase);
    }

    @Bean
    IInsuranceProductGateway insuranceProductGateway(InsuranceProductRepository insuranceProductRepository, InsuranceProductEntityMapper insuranceProductEntityMapper){
        return new InsuranceProductRepositoryGateway(insuranceProductRepository, insuranceProductEntityMapper);
    }

    @Autowired
    FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;

    @Bean
    InsuranceProductEntityMapper insuranceProductEntityMapper(){
        return new InsuranceProductEntityMapper();
    }

    @Bean
    InsuranceProductDTOMapper insuranceProductDTOMapper(){
        return new InsuranceProductDTOMapper();
    }

}
