package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceProductRepository extends CrudRepository<InsuranceProductEntity, Long> {
}
