package br.com.vilevidya.backendchallenge.infrastructure.gateway.InsuranceProducts;

import br.com.vilevidya.backendchallenge.application.interfaces.InsuranceProducts.IInsuranceProductGateway;
import br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts.InsuranceProduct;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntity;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductEntityPK;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class InsuranceProductRepositoryGateway implements IInsuranceProductGateway {
    private static final Logger log = LoggerFactory.getLogger(InsuranceProductRepositoryGateway.class);
    // Dependency Injection
    private final InsuranceProductRepository insuranceProductRepository;
    private final InsuranceProductEntityMapper insuranceProductEntityMapper;

    public InsuranceProductRepositoryGateway(InsuranceProductRepository insuranceProductRepository, InsuranceProductEntityMapper insuranceProductEntityMapper) {
        this.insuranceProductRepository = insuranceProductRepository;
        this.insuranceProductEntityMapper = insuranceProductEntityMapper;
    }

    @Override
    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProductDomainObject) {
        log.info("method=createInsuranceProduct, step=starting, name={}", insuranceProductDomainObject);
        Optional<InsuranceProductEntity> foundInsuranceProductEntity = findByInsuranceProductEntityPK(
                insuranceProductEntityMapper.toEntity(insuranceProductDomainObject).getInsuranceProductEntityPK()
        );
        if(foundInsuranceProductEntity.isPresent()){
            insuranceProductDomainObject.setId(foundInsuranceProductEntity.get().getId());
        }else{
            insuranceProductDomainObject.setId(UUID.randomUUID());
        }
        InsuranceProduct result = insuranceProductEntityMapper.toDomainObject(
                insuranceProductRepository.save(
                        insuranceProductEntityMapper.toEntity(insuranceProductDomainObject)
                )
        );
        log.info("method=createInsuranceProduct, step=finished, result={}", result);
        return result;

    }

    private Optional<InsuranceProductEntity> findByInsuranceProductEntityPK(InsuranceProductEntityPK insuranceProductEntityPK){
        log.info("method=createInsuranceProduct, step=starting, name={}", insuranceProductEntityPK);
        Optional<InsuranceProductEntity> result = insuranceProductRepository.findByInsuranceProductEntityPK(insuranceProductEntityPK);
        log.info("method=createInsuranceProduct, step=finished, result={}", result);
        return result;

    }
}
