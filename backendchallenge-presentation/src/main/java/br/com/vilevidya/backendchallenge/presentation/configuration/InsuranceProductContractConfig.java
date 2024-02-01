package br.com.vilevidya.backendchallenge.presentation.configuration;

import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceProductContractConfig {
    @Bean
    InsuranceProductDTOMapper insuranceProductDTOMapper(){
        return new InsuranceProductDTOMapper();
    }
}
