package br.com.vilevidya.backendchallenge.infrastructure.gateway.exceptions;

public class InsuranceTypeNotFoundException extends Exception{
    public InsuranceTypeNotFoundException(String message){
        super(message);
    }
}
