package br.com.vilevidya.backendchallenge.presentation.advice;

import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.presentation.contracts.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<String> handleArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return GenericResponse.error(errorMap.toString());
        //return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public GenericResponse<String> handleArgumentNotValid(HttpMessageNotReadableException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("mensagem_erro:", ex.getMessage());
        return GenericResponse.error(errorMap.toString());
        //return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsuranceTypeNotFoundException.class)
    public GenericResponse<String> handleBusinessException(InsuranceTypeNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("mensagem_erro", ex.getMessage());
        return GenericResponse.error(errorMap.toString());
    }
}
