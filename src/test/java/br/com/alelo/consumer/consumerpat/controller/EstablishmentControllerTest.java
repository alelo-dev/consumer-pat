package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.JsonTestUtil;
import br.com.alelo.consumer.consumerpat.domain.dto.ErrorDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class EstablishmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Integer ID_ENTITY ;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @Order(1)
    void should_save() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/establishment")
                .content(JsonTestUtil.asJsonString(getDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        EstablishmentDTO dtoResponse =  this.objectMapper.readValue(response,  EstablishmentDTO.class);
        ID_ENTITY = dtoResponse.getId();
        assertEquals(CardType.FOOD, dtoResponse.getCardTypeAccepted());
        assertEquals("Temakeria Russa", dtoResponse.getEstablishmentName());
    }

    @Test
    @Order(2)
    void should_listAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/establishment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        List<EstablishmentDTO> establishmentDTOList =  this.objectMapper.readValue(response,   new TypeReference<List<EstablishmentDTO>>() { });

        assertNotNull(response);
        assertEquals(1, establishmentDTOList.size());
    }


    private EstablishmentDTO getDTO() {
        EstablishmentDTO dto = EstablishmentDTO.builder()
                .establishmentName("Temakeria Russa")
                .cardTypeAccepted(CardType.FOOD)
                .build();
        return dto;
    }
}