package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.FOOD;

@WebMvcTest(EstablishmentController.class)
class EstablishmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EstablishmentService establishmentService;

    private Establishment establishment;

    @BeforeEach
    public void setUp() {

        establishment = Establishment.builder().id(1L).name("test").type(FOOD).build();
    }

    @Test
    public void testListAllEstablishments() throws Exception {

        final List<Establishment> establishments = List.of(establishment);

        when(establishmentService.findAll()).thenReturn(establishments);

        mockMvc.perform(get("/establishment/listAll")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(establishments)));
    }

    @Test
    public void testFindEstablishment() throws Exception {

        final Long establishmentId = 1L;

        when(establishmentService.findById(eq(establishmentId))).thenReturn(establishment);

        mockMvc.perform(get("/establishment/findById/" + establishmentId)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(establishment)));
    }

    @Test
    public void testCreateEstablishment() throws Exception {

        when(establishmentService.createEstablishment(eq(establishment))).thenReturn(establishment);

        mockMvc.perform(post("/establishment/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(establishment))).andExpect(status().isOk());
    }

    @Test
    public void testUpdateEstablishment() throws Exception {

        doNothing().when(establishmentService).updateEstablishment(eq(establishment));

        mockMvc.perform(put("/establishment/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(establishment))).andExpect(status().isOk());
    }
}