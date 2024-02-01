package br.com.vilevidya.backendchallenge.infrastructure;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

// we'll annotate SpringBootConfiguration here
// instead of inside InfrastructureConfiguration
// to avoid duplicates of SpringBootConfiguration,
// which is a bad practice and should be avoided.
@SpringBootConfiguration
@EnableAutoConfiguration
public class InfrastructureTestConfiguration extends InfrastructureConfiguration {
}
