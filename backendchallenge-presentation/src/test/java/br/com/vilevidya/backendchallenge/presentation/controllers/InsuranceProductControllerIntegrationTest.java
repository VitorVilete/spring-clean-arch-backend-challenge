package br.com.vilevidya.backendchallenge.presentation.controllers;


import br.com.vilevidya.backendchallenge.application.usecases.exceptions.InsuranceTypeNotFoundException;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceProducts.InsuranceProductRepository;
import br.com.vilevidya.backendchallenge.infrastructure.persistence.InsuranceTypes.InsuranceTypeLocalRepository;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.PutInsuranceProductRequest;
import br.com.vilevidya.backendchallenge.application.usecases.contracts.PutInsuranceProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.UUID;
import java.util.regex.Pattern;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class InsuranceProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InsuranceProductRepository insuranceProductRepository;

    @Autowired
    private InsuranceTypeLocalRepository insuranceTypeLocalRepository;

    private PutInsuranceProductResponse putInsuranceProductResponse;

    private PutInsuranceProductRequest putInsuranceProductRequest;
    private PutInsuranceProductRequest putInsuranceProductRequestWithPrecoTarifado;

    private PutInsuranceProductRequest putInsuranceProductRequestBadRequestArgumentNotValidException;

    private PutInsuranceProductRequest putInsuranceProductInsuranceTypeNotFoundException;

    Pattern UUID_REGEX;

    String precoTarifadoPath;
    String idPath;

    String insuranceProductPutCreatePath;

    @BeforeEach
    public void init(){
        UUID_REGEX = Pattern
                .compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        precoTarifadoPath = "preco_tarifado";
        idPath = "id";
        insuranceProductPutCreatePath = "/produtos";
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
        putInsuranceProductRequestWithPrecoTarifado = new PutInsuranceProductRequest(
                "Seguro Auto Individual",
                "AUTO",
                50.00,
                55.00);
        putInsuranceProductRequestBadRequestArgumentNotValidException = new PutInsuranceProductRequest(
                "Seguro Auto Individual",
                "AUTO",
                -1.00,
                null);
        putInsuranceProductInsuranceTypeNotFoundException = new PutInsuranceProductRequest(
                "Seguro Auto Individual",
                "AUSTO",
                50.00,
                null);
    }

    @Test
    public void InsuranceProductController_Create_ReturnCreated() throws Exception {
        MvcResult response = mockMvc.perform(put(insuranceProductPutCreatePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putInsuranceProductRequest)))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(precoTarifadoPath).isNumber())
                .andExpect(jsonPath(idPath, matchesPattern(UUID_REGEX)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    public void InsuranceProductController_Create_ReturnCreatedWithPrecoTarifado() throws Exception {
        MvcResult response = mockMvc.perform(put(insuranceProductPutCreatePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putInsuranceProductRequestWithPrecoTarifado)))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(precoTarifadoPath).isNumber())
                .andExpect(jsonPath(idPath, matchesPattern(UUID_REGEX)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseJsonString = response.getResponse().getContentAsString();
        Number precoTarifado = JsonPath.parse(responseJsonString).read(precoTarifadoPath);
        Assertions.assertThat(precoTarifado).isEqualTo(putInsuranceProductRequestWithPrecoTarifado.getPreco_tarifado());
    }

    @Test
    public void InsuranceProductController_Create_ReturnBadRequestArgumentNotValidException() throws Exception {
        MvcResult response = mockMvc.perform(put(insuranceProductPutCreatePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putInsuranceProductRequestBadRequestArgumentNotValidException)))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertThat(response.getResolvedException() instanceof MethodArgumentNotValidException).isTrue();
    }

    @Test
    public void InsuranceProductController_Create_ReturnBadRequestHttpMessageNotReadableException() throws Exception {
        MvcResult response = mockMvc.perform(put(insuranceProductPutCreatePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("")))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertThat(response.getResolvedException() instanceof HttpMessageNotReadableException).isTrue();
    }

    @Test
    public void InsuranceProductController_Create_ReturnInternalServerErrorInsuranceTypeNotFoundException() throws Exception {
        MvcResult response = mockMvc.perform(put(insuranceProductPutCreatePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putInsuranceProductInsuranceTypeNotFoundException)))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andReturn();

        Assertions.assertThat(response.getResolvedException() instanceof InsuranceTypeNotFoundException).isTrue();
    }
}
