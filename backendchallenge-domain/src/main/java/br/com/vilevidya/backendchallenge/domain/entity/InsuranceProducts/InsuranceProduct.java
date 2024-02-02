package br.com.vilevidya.backendchallenge.domain.entity.InsuranceProducts;

import br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes.InsuranceType;

import java.util.UUID;


public class InsuranceProduct {
    private InsuranceType insuranceType;
    private UUID id;
    private String name;
    private double basePrice;
    private double taxedPrice;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTaxedPrice(double taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getTaxedPrice() {
        return taxedPrice;
    }

    public InsuranceProduct(InsuranceProductBuilder insuranceProductBuilder) {
        this.insuranceType = insuranceProductBuilder.insuranceType;
        this.id = insuranceProductBuilder.id;
        this.name = insuranceProductBuilder.name;
        this.basePrice = insuranceProductBuilder.basePrice;
        this.taxedPrice = insuranceProductBuilder.taxedPrice;
    }

    //Builder
    public static class InsuranceProductBuilder{
        private InsuranceType insuranceType;
        UUID id;
        String name;
        double basePrice;
        double taxedPrice;
        public InsuranceProductBuilder() {
        }
        public InsuranceProductBuilder(InsuranceType insuranceType, UUID id, String name, double basePrice, double taxedPrice) {
            this.insuranceType = insuranceType;
            this.id = id;
            this.name = name;
            this.basePrice = basePrice;
            this.taxedPrice = taxedPrice;
        }
        public InsuranceProductBuilder setInsuranceType(InsuranceType insuranceType){
            this.insuranceType = insuranceType;
            return this;
        }
        public InsuranceProductBuilder setId(UUID id){
            this.id = id;
            return this;
        }
        public InsuranceProductBuilder setName(String name){
            this.name = name;
            return this;
        }
        public InsuranceProductBuilder setBasePrice(double basePrice){
            this.basePrice = basePrice;
            return this;
        }
        public InsuranceProductBuilder setTaxedPrice(double taxedPrice){
            this.taxedPrice = taxedPrice;
            return this;
        }
        public InsuranceProduct build(){
            return new InsuranceProduct(this);
        }
    }
}
