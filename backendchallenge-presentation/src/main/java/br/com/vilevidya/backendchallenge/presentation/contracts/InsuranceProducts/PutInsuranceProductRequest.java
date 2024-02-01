package br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts;

import jakarta.validation.constraints.*;

public class PutInsuranceProductRequest {

    @NotBlank
    String nome;
    @NotBlank
    String categoria;

    @NotNull
    @DecimalMin(value="0.01")
    @Digits(integer=11, fraction = 2)
    Number preco_base;
    @DecimalMin(value="0.01")
    @Digits(integer=11, fraction = 2)
    Number preco_tarifado;

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

    public PutInsuranceProductRequest(String nome, String categoria, Number preco_base, Number preco_tarifado) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco_base = preco_base;
        this.preco_tarifado = preco_tarifado;
    }
}
