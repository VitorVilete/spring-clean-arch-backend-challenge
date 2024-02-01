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

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public InsuranceProductEntityPK(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public InsuranceProductEntityPK() {
    }
}
