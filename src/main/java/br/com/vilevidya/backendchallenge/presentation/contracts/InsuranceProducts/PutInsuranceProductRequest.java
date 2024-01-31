package br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PutInsuranceProductRequest {
    String id;
    @NotBlank
    String nome;
    @NotBlank
    String categoria;

    @NotNull
    @DecimalMin(value="0.01", inclusive = true)
    @Digits(integer=11, fraction = 2)
    Number preco_base;
    @DecimalMin(value="0.01", inclusive = true)
    @Digits(integer=11, fraction = 2)
    Number preco_tarifado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Number getPreco_base() {
        return preco_base;
    }

    public void setPreco_base(Number preco_base) {
        this.preco_base = preco_base;
    }

    public Number getPreco_tarifado() {
        return preco_tarifado;
    }

    public void setPreco_tarifado(Number preco_tarifado) {
        this.preco_tarifado = preco_tarifado;
    }

    public PutInsuranceProductRequest(String id, String nome, String categoria, Number preco_base, Number preco_tarifado) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco_base = preco_base;
        this.preco_tarifado = preco_tarifado;
    }
}
