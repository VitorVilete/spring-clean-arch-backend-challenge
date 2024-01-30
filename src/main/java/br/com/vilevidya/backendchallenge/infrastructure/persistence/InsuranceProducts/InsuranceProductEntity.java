package br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "INSURANCE_PRODUCT")
public class InsuranceProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "BASE_PRICE", precision = 11, scale = 2)
    private BigDecimal basePrice;
    @Column(name = "TAXED_PRICE", precision = 11, scale = 2)
    private BigDecimal taxedPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public void setTaxedPrice(BigDecimal taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    public InsuranceProductEntity(String name, String category, BigDecimal basePrice, BigDecimal taxedPrice) {
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.taxedPrice = taxedPrice;
    }
}
