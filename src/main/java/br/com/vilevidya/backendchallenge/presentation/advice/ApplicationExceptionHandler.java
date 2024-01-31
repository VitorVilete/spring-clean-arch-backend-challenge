package br.com.vilevidya.backendchallenge.presentation.advice;

import br.com.vilevidya.backendchallenge.infrastructure.gateway.exceptions.InsuranceTypeNotFoundException;
import org.springframework.http.HttpStatus;
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
    public Map<String, String> handleArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InsuranceTypeNotFoundException.class)
    public Map<String, String> handleBusinessException(InsuranceTypeNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("mensagem_erro", ex.getMessage());
        return errorMap;
    }
}
