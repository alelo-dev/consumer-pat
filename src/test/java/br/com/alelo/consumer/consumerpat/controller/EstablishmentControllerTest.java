package br.com.alelo.consumer.consumerpat.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EstablishmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstablishmentService service;

    @Test
    public void whenGetListEstablisment_thenReturnCorrect() throws Exception {
        List<Establishment> establishments = createEstablishmentList();
        when(service.listAll(any())).thenReturn(new PageImpl<>(establishments));

        this.mockMvc.perform(get("/establishment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(establishments.size())))
                .andExpect(jsonPath("$.result.size()", is(establishments.size())));
    }

    @Test
    public void whenGetEmptytListEstablisment_thenReturnCorrect() throws Exception {
        when(service.listAll(any())).thenReturn(Page.empty());

        this.mockMvc.perform(get("/establishment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(0)))
                .andExpect(jsonPath("$.result.size()", is(0)));
    }

    @Test
    public void whenFindOneExistingEstablishment_thenReturnCorrect() throws Exception {
        when(service.findById(15L)).thenReturn(Optional.of(createEstablishiment("EC ID 15")));

        this.mockMvc.perform(get("/establishment/15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.establishmentName", is("EC ID 15")));
    }

    @Test
    public void whenFindOneNonExistingEstablishment_thenReturnNotFound() throws Exception {
        when(service.findById(15L)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/establishment/15"))
                .andExpect(status().isNotFound());
    }

    private List<Establishment> createEstablishmentList() {

        return List.of(
                createEstablishiment("EC 01"),
                createEstablishiment("EC 02"),
                createEstablishiment("EC 03"),
                createEstablishiment("EC 04"),
                createEstablishiment("EC 05"),
                createEstablishiment("EC 06"),
                createEstablishiment("EC 07"),
                createEstablishiment("EC 08"),
                createEstablishiment("EC 09"),
                createEstablishiment("EC 10")
        );
    }

    private Establishment createEstablishiment(final String name) {
        return Establishment.builder()
                .establishmentName(name)
                .build();
    }

}
