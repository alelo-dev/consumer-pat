package br.com.alelo.consumer.consumerpat.controller;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.FOOD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import br.com.alelo.consumer.consumerpat.dto.SaleDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import br.com.alelo.consumer.consumerpat.service.SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EstablishmentService establishmentService;

    @MockBean
    CardService cardService;

    @MockBean
    SaleService saleService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void whenPerformSaleWithValidData_thenReturnSuccess() throws Exception {
        when(establishmentService.findById(10L)).thenReturn(Optional.of(mock(Establishment.class)));
        when(cardService.findByNumberAndProductType("111111", FOOD)).thenReturn(Optional.of(mock(Card.class)));

        this.mockMvc.perform(
                post("/establishment/10/sale/product/1/sell")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                SaleDto.builder()
                                        .cardNumber("111111")
                                        .productDescription("desc")
                                        .value(15.2)
                                        .build())
                        )
        )
                .andExpect(status().isOk());
    }

    @Test
    public void whenPerformSaleWithInvalidProduct_thenReturnNotFound() throws Exception {
        when(establishmentService.findById(10L)).thenReturn(Optional.of(mock(Establishment.class)));
        when(cardService.findByNumberAndProductType("111111", FOOD)).thenReturn(Optional.of(mock(Card.class)));

        this.mockMvc.perform(
                post("/establishment/10/sale/product/10/sell")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                SaleDto.builder()
                                        .cardNumber("111111")
                                        .productDescription("desc")
                                        .value(15.2)
                                        .build())
                        )
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product not found"));
    }

    @Test
    public void whenPerformSaleWithInvalidEstablishment_thenReturnNotFound() throws Exception {
        when(establishmentService.findById(10L)).thenReturn(Optional.empty());
        when(cardService.findByNumberAndProductType("111111", FOOD)).thenReturn(Optional.of(mock(Card.class)));

        this.mockMvc.perform(
                post("/establishment/10/sale/product/1/sell")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                SaleDto.builder()
                                        .cardNumber("111111")
                                        .productDescription("desc")
                                        .value(15.2)
                                        .build())
                        )
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Establishment not found"));
    }

    @Test
    public void whenPerformSaleWithInvalidCard_thenReturnNotFound() throws Exception {
        when(establishmentService.findById(10L)).thenReturn(Optional.of(mock(Establishment.class)));
        when(cardService.findByNumberAndProductType("111111", FOOD)).thenReturn(Optional.empty());

        this.mockMvc.perform(
                post("/establishment/10/sale/product/1/sell")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                SaleDto.builder()
                                        .cardNumber("111111")
                                        .productDescription("desc")
                                        .value(15.2)
                                        .build())
                        )
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Card not found for product FOOD"));
    }

}
