package br.com.vilevidya.backendchallenge.presentation.controllers;

import br.com.vilevidya.backendchallenge.application.usecases.InsuranceProducts.CreateInsuranceProductUseCase;
import br.com.vilevidya.backendchallenge.application.usecases.InsuranceTypes.FindInsuranceTypeByNameUseCase;
import br.com.vilevidya.backendchallenge.presentation.advice.ApplicationExceptionHandler;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.InsuranceProductDTOMapper;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.presentation.contracts.InsuranceProducts.PutInsuranceProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InsuranceProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateInsuranceProductUseCase createInsuranceProductUseCase;
    @MockBean
    private FindInsuranceTypeByNameUseCase findInsuranceTypeByNameUseCase;

    @MockBean
    private InsuranceProductDTOMapper insuranceProductDTOMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private PutInsuranceProductResponse putInsuranceProductResponse;

    private PutInsuranceProductRequest putInsuranceProductRequest;

    @BeforeEach
    public void init(){
        putInsuranceProductResponse = new PutInsuranceProductResponse(
                UUID.randomUUID().toString(),
                "Seguro Auto Individual",
                "AUTO",
                50.00,
                null);
        putInsuranceProductRequest = new PutInsuranceProductRequest(
                "Seguro Auto Individual",
                "AUTO",
                50.00,
                null);
    }
    @Test
    public void InsuranceProductController_Create_ReturnCreated() throws Exception {
        given(createInsuranceProductUseCase.createInsuranceProduct(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putInsuranceProductRequest)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}