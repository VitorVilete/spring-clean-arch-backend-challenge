package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InsuranceProductRepository extends CrudRepository<InsuranceProductEntity, Long> {
    Optional<InsuranceProductEntity> findByInsuranceProductEntityPK(InsuranceProductEntityPK insuranceProductEntityPK);
}
