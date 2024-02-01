package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes;

import org.springframework.data.repository.CrudRepository;

public interface InsuranceTypeRepository extends CrudRepository<InsuranceTypeEntity, Long> {
    InsuranceTypeEntity findByName(String name);
}
