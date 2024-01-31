package br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts;


public record PutInsuranceProductResponse(String id, String nome, String categoria, Number preco_base, Number preco_tarifado) {
}
