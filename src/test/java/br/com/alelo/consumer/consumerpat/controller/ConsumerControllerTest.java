package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.JsonTestUtil;
import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static Integer ID_ENTITY;

    @Test
    @Order(1)
    void testPost() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/consumer/createConsumer")
                .content(JsonTestUtil.asJsonString(getDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ConsumerDTO dtoResponse =  this.objectMapper.readValue(response,  ConsumerDTO.class);
        this.ID_ENTITY = dtoResponse.getId();
        assertEquals(123456, dtoResponse.getDocumentNumber());

        assertNotNull(response);
    }

    @Test
    @Order(2)
    void testGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/consumer/consumerList")
                .param("page", "0")
                .param("size", "5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertNotNull(response);
        assertTrue(response.contains("{\"content\":[{\"id\":1,\"name\":\"Sakura Card Captors\",\"documentNumber\":123456"));
    }

    @Test
    @Order(3)
    void testGetByIdNotFound() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/consumer/{id}", 300)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertNotNull(response);
    }

    @Test
    @Order(4)
    void testGetByIdFound() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/consumer/{id}", ID_ENTITY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ConsumerDTO dtoResponse =  this.objectMapper.readValue(response,  ConsumerDTO.class);

        assertEquals(123456, dtoResponse.getDocumentNumber());
        assertEquals("Sakura Card Captors", dtoResponse.getName());
        assertEquals("João Pessoa", dtoResponse.getCity());
        assertEquals("Rua da mansão Bly", dtoResponse.getStreet());
        assertEquals("sakura@alelo.com.br", dtoResponse.getEmail());
        assertEquals(41, dtoResponse.getNumber());
        assertEquals("77777777", dtoResponse.getMobilePhoneNumber());
        assertEquals("99999999", dtoResponse.getResidencePhoneNumber());
    }

    @Test
    @Order(5)
    void should_execute_put() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/consumer/{id}", ID_ENTITY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ConsumerDTO dtoGetForUpdate =  this.objectMapper.readValue(response,  ConsumerDTO.class);

        dtoGetForUpdate.setId(ID_ENTITY);
        dtoGetForUpdate.setName("Alelino da Silva");
        dtoGetForUpdate.setCountry("China");

        result = mockMvc.perform(MockMvcRequestBuilders.put("/consumer/updateConsumer")
                .content(JsonTestUtil.asJsonString(dtoGetForUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ConsumerDTO dtoResponseAfterUpdate =  this.objectMapper.readValue(response,  ConsumerDTO.class);

        assertEquals(123456, dtoResponseAfterUpdate.getDocumentNumber());
        assertEquals("Alelino da Silva", dtoResponseAfterUpdate.getName());
        assertEquals("China", dtoResponseAfterUpdate.getCountry());
    }

    @Test
    @Order(6)
    void should_execute_put_without_id_request() throws Exception {
        ConsumerDTO dto =  getDTO();
        dto.setId(null);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/updateConsumer")
                .content(JsonTestUtil.asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertNotNull(response);
    }

    private ConsumerDTO getDTO() {
        LocalDate birthDate =  LocalDate.of(1982,12,17);
        ConsumerDTO dto = ConsumerDTO.builder()
                .birthDate(Date.from(birthDate.atStartOfDay()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()))
                .city("João Pessoa")
                .country("Brazil")
                .documentNumber(123456)
                .name("Sakura Card Captors")
                .street("Rua da mansão Bly")
                .number(41)
                .email("sakura@alelo.com.br")
                .portalCode(45850000)
                .phoneNumber("88888888")
                .residencePhoneNumber("99999999")
                .mobilePhoneNumber("77777777")
                .build();

        return dto;
    }

    @Test
    void listAllConsumers() {
    }

    @Test
    void createConsumer() {
    }

    @Test
    void updateConsumer() {
    }
}