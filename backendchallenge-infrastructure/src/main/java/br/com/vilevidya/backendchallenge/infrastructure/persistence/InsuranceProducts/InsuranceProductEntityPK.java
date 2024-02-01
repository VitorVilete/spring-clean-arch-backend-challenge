package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class InsuranceProductEntityPK implements Serializable {

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public InsuranceProductEntityPK() {
    }
    public InsuranceProductEntityPK(InsuranceProductEntityPKBuilder insuranceProductEntityPKBuilder) {
        this.name = insuranceProductEntityPKBuilder.name;
        this.category = insuranceProductEntityPKBuilder.category;
    }


    public static class InsuranceProductEntityPKBuilder{
        private String name;
        private String category;

        public InsuranceProductEntityPKBuilder(){
        }
        public InsuranceProductEntityPKBuilder(String name, String category){
            this.name = name;
            this.category = category;
        }
        public InsuranceProductEntityPKBuilder setName(String name){
            this.name = name;
            return this;
        }
        public InsuranceProductEntityPKBuilder setCategory(String category){
            this.category = category;
            return this;
        }
        public InsuranceProductEntityPK build(){
            return new InsuranceProductEntityPK(this);
        }
    }

}
