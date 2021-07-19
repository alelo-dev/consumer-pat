package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerCreateDto;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static br.com.alelo.consumer.consumerpat.service.testutils.TestUtils.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerController.class)
class ConsumerControllerTest {

    private final String baseUrl = "http://localhost:%s/%s/%s";

    @MockBean
    ConsumerService consumerService;

    @MockBean
    ConsumerRepository consumerRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenCreateConsumerIsCalled_ShouldReturns201CreatedCodeAndResponseEntityOfConsumerWithIdAndUri() throws Exception {
        String uri = String.format(baseUrl, 8080, "consumer", "createConsumer");

        ConsumerCreateDto consumerCreateDto = buildConsumerCreatedDto();
        Consumer consumer = consumerCreateDto.toDomainObject();
        consumer.setId(1L);

        when(consumerService.registerConsumer(any())).thenReturn(consumer);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(consumerCreateDto).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    public void whenListAllConsumersIsCalled_ShouldReturnsListOfConsumers() throws Exception {
        String uri = String.format(baseUrl, 8080, "consumer", "consumerList");

        List<Consumer> consumers = buildConsumersList(10);

        when(consumerService.getAllConsumersPaginate(any())).thenReturn(consumers);

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(consumers.size())));
    }

    @Test
    public void whenUpdateConsumerIsCalled_ShouldReturn200() throws Exception {
        String uri = String.format(baseUrl, 8080, "consumer", "updateConsumer/1");

        ConsumerUpdateDto consumerUpdateDto = buildConsumerUpdateDto();

        doNothing().when(consumerService).updateConsumer(any(),any());

        this.mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(consumerUpdateDto).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
    }
}