package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.BaseIntegrationTest;
import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.builder.CreateConsumerBuilder.fullCreateConsumerDTO;
import static br.com.alelo.consumer.consumerpat.builder.UpdateConsumerBuilder.fullUpdateConsumerDTO;
import static br.com.alelo.consumer.consumerpat.service.ConsumerServiceTest.assertCreateConsumer;
import static br.com.alelo.consumer.consumerpat.service.ConsumerServiceTest.assertUpdateConsumer;
import static java.util.Objects.nonNull;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConsumerControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ConsumerRepository consumerRepository;

    @BeforeEach
    public void resetRepository() {
        cleanRepository();
    }

    @AfterEach
    public void cleanRepository() {
        consumerRepository.deleteAll();
    }

    @Test
    public void shouldCreateAndUpdateConsumerWithAllFields() throws Exception {
        final Consumer consumer = shouldCreateConsumerAndGetIt();
        shouldUpdateConsumer(consumer);
        shouldFindAll();
    }

    @Test
    public void shouldNotUpdateNonExistentConsumer() throws Exception {
        final UpdateConsumerDTO updateRequest = fullUpdateConsumerDTO();
        mvc.perform(patch("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andExpect(jsonPath("$.result.userMessage", containsString(Constants.CONSUMER_NOT_FOUND)))
                .andExpect(jsonPath("$.result.developerMessage", containsString(updateRequest.getId().toString())));
    }

    private void shouldUpdateConsumer(Consumer consumer) throws Exception {
        final UpdateConsumerDTO updateRequest = getUpdateConsumer(consumer);
        mvc.perform(patch("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateRequest)))
                .andExpect(status().isNoContent());
        Consumer consumerDB = getConsumerDB(consumer.getId());
        assertUpdateConsumer(updateRequest, consumerDB);
    }

    private UpdateConsumerDTO getUpdateConsumer(Consumer consumer) {
        final UpdateConsumerDTO updateRequest = fullUpdateConsumerDTO();
        updateRequest.setId(consumer.getId());
        updateRequest.getCards().forEach(it -> {
            if (nonNull(it) && nonNull(it.getId())) {
                it.setId(consumer.getCards().stream()
                        .filter(c -> c.getNumber().split("#")[1].equals(it.getNumber().split("#")[1]))
                        .map(Card::getId).findFirst().orElse(null));
            }
        });
        return updateRequest;
    }

    private Consumer shouldCreateConsumerAndGetIt() throws Exception {
        final CreateConsumerDTO createRequest = fullCreateConsumerDTO();
        MockHttpServletResponse response = mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andReturn()
                .getResponse();

        BaseHttpResponse<ConsumerIdDTO> body = getResponseBody(response);
        final Long id = body.getResult().getId();
        Consumer consumerDB = getConsumerDB(id);

        assertEquals(id, consumerDB.getId());
        assertCreateConsumer(createRequest, consumerDB);
        return consumerDB;
    }

    private void shouldFindAll() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/consumer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andReturn()
                .getResponse();

        BaseHttpResponse<List<ConsumerDTO>> body = getResponseBodyList(response);

        List<Consumer> consumerDB = findAll();

        final List<ConsumerDTO> result = body.getResult();
        assertEquals(consumerDB.size(), result.size());
    }

    @SneakyThrows
    private BaseHttpResponse<List<ConsumerDTO>> getResponseBodyList(MockHttpServletResponse response) {
        BaseHttpResponse<List<ConsumerDTO>> result = asBaseHttpResponseList(response.getContentAsString());
        assertNotNull(result);
        assertNotNull(result.getResult());
        return result;
    }

    @SneakyThrows
    private BaseHttpResponse<ConsumerIdDTO> getResponseBody(MockHttpServletResponse response) {
        BaseHttpResponse<ConsumerIdDTO> result = asBaseHttpResponse(response.getContentAsString(), ConsumerIdDTO.class);
        assertNotNull(result);
        assertNotNull(result.getResult());
        return result;
    }

    private Consumer getConsumerDB(Long id) {
        assertNotNull(id);
        final Optional<Consumer> optional = consumerRepository.findById(id);
        assertTrue(optional.isPresent());
        return optional.get();
    }

    private List<Consumer> findAll() {
        return consumerRepository.findAll();
    }
}