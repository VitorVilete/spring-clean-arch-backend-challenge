package br.com.vilevidya.backendchallenge.application.usecases.contracts;


public record PutInsuranceProductResponse(String id, String nome, String categoria, Number preco_base, Number preco_tarifado) {
}
