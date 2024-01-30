package br.com.vilevidya.backendchallenge.infrastructure.config.InsuranceTypes;


import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceTypes.IInsuranceTypeGateway;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeUseCase;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes.InsuranceTypeEntityMapper;
import br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceTypes.InsuranceTypeRepositoryGateway;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceTypeConfig {
    @Bean
    FindInsuranceTypeUseCase findInsuranceType(IInsuranceTypeGateway insuranceTypeGateway){
        return new FindInsuranceTypeUseCase(insuranceTypeGateway);
    }

    @Bean
    IInsuranceTypeGateway insuranceTypeGateway(InsuranceTypeRepository insuranceTypeRepository, InsuranceTypeEntityMapper insuranceTypeEntityMapper){
        return new InsuranceTypeRepositoryGateway(insuranceTypeRepository, insuranceTypeEntityMapper);
    }

    @Bean
    InsuranceTypeEntityMapper insuranceTypeEntityMapper(){
        return new InsuranceTypeEntityMapper();
    }

}