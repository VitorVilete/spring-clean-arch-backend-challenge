package br.com.vilevidya.backendchallenge.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// using this Configuration as a way to make a module load its beans during testing
// extend this class in the test path as done in InfrastructureTestConfiguration
@Configuration
@ComponentScan
public class InfrastructureConfiguration {
}
