package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
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
class ConsumerControllerTest extends AbstractControllerTest {

    private static final String LIST_ALL_CONSUMERS_ENDPOINT = "/consumer/list-all-consumers";

    private static final String UPDATE_CONSUMER_ENDPOINT = "/consumer/update-consumer";

    private static final String CREATE_CONSUMER_ENDPOINT = "/consumer/create-consumer";

    @MockBean
    private ConsumerService consumerService;

    @SneakyThrows
    @Test
    void List_all_consumers_when_request_is_ok() {
        this.mockMvc.perform(get(LIST_ALL_CONSUMERS_ENDPOINT)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void Create_consumer_when_request_is_ok() {
        this.mockMvc.perform(post(CREATE_CONSUMER_ENDPOINT)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().build())))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void Update_consumer_when_request_is_ok() {
        this.mockMvc.perform(put(UPDATE_CONSUMER_ENDPOINT.concat("/{id}"), 1)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().id(1L).build())))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void List_all_consumers_when_request_fails() {
        when(consumerService.listAllConsumers(any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(get(LIST_ALL_CONSUMERS_ENDPOINT)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void Create_consumer_when_request_fails() {
        when(consumerService.save(any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(post(CREATE_CONSUMER_ENDPOINT)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().build())))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void Update_consumer_when_request_has_different_values() {
        this.mockMvc.perform(put(UPDATE_CONSUMER_ENDPOINT.concat("/{id}"), 1)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(Consumer.builder().build())))
                .andExpect(status().isNoContent());
    }
}
