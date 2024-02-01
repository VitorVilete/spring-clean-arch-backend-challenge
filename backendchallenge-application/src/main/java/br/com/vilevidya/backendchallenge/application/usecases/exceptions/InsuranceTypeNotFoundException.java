package br.com.vilevidya.backendchallenge.application.usecases.exceptions;

public class InsuranceTypeNotFoundException extends Exception{
    public InsuranceTypeNotFoundException(String message){
        super(message);
    }
}
