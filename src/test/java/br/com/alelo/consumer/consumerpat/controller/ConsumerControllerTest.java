package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerController.class)
class ConsumerControllerTest extends AbstractControllerTest{

    private static final String CONSUMER = "/consumer";
    private static final String PATH_VAR_ID = "/{id}";
    private static final int ID = 1;

    @MockBean
    private IConsumerService service;

    @SneakyThrows
    @Test
    void listAllConsumer_whenGetRequestIsOk_thenReturnIsOk() {
        this.mockMvc.perform(get(CONSUMER)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void listAllConsumer_whenGetRequestFailedService_thenReturnBadRequest() {
        when(service.findAll(any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(get(CONSUMER)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void createConsumer_whenPostRequestIsOk_thenReturnIsOk() {
        this.mockMvc.perform(post(CONSUMER)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().build())))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void createConsumer_whenPostRequestFailedService_thenReturnBadRequest() {
        when(service.save(any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(post(CONSUMER)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().build())))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void updateConsumer_whenPutRequestIsOk_thenReturnIsOk() {
        this.mockMvc.perform(put(CONSUMER.concat(PATH_VAR_ID), ID)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().id(1L).build())))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void updateConsumer_whenPutRequestDifferentValues_thenNoContent() {
        this.mockMvc.perform(put(CONSUMER.concat(PATH_VAR_ID), ID)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().build())))
                .andExpect(status().isNoContent());
    }
}
