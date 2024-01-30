package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class InsuranceProductEntityPK implements Serializable {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CATEGORY")
    private String category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public InsuranceProductEntityPK(UUID id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public InsuranceProductEntityPK() {
    }
}
