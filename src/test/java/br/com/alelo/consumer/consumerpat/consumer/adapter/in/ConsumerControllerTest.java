package br.com.alelo.consumer.consumerpat.consumer.adapter.in;

import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.ListConsumersCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.ListConsumersUseCase;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.NewConsumer;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.RegisterConsumerCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.RegisterConsumerUseCase;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.UpdateConsumer;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.UpdateConsumerCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.UpdateConsumerUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ConsumerController.class)
class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterConsumerUseCase registerConsumerUseCase;

    @MockBean
    private ListConsumersUseCase listConsumersUseCase;

    @MockBean
    private UpdateConsumerUseCase updateConsumerUseCase;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("Consumer controller success cases tests")
    class SuccessCases {

        @Test
        void testListConsumers() throws Exception {

            var command = ListConsumersCommand.of(PageRequest.of(0, 20));

            mockMvc.perform(get("/consumers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andDo(print());

            then(listConsumersUseCase).should()
                    .listAll(command);
        }

        @Test
        void testRegisterConsumer() throws Exception {

            var newConsumer = new NewConsumer("John Smith", 12345,
                    LocalDate.of(1985, Month.JANUARY, 1));

            var command = RegisterConsumerCommand.of(newConsumer);

            given(registerConsumerUseCase.registerConsumer(command))
                    .willReturn(new ConsumerApiModel());

            mockMvc.perform(post("/consumers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newConsumer)))
                    .andExpect(status().isCreated());

            then(registerConsumerUseCase).should()
                    .registerConsumer(command);
        }

        @Test
        void testUpdateConsumer() throws Exception {

            var consumerId = 1;
            var newConsumer = new UpdateConsumer("John Smith", 12345,
                    LocalDate.of(1985, Month.JANUARY, 1));
            var consumerModelApi = new ConsumerApiModel(consumerId, newConsumer.getDocumentNumber(),
                    newConsumer.getName(), newConsumer.getBirthDate());

            var command = UpdateConsumerCommand.of(consumerId, newConsumer);

            given(updateConsumerUseCase.updateConsumer(command))
                    .willReturn(consumerModelApi);

            mockMvc.perform(put("/consumers/{consumerId}", consumerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newConsumer)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.consumerId").value(consumerId))
                    .andDo(print());

            then(updateConsumerUseCase).should()
                    .updateConsumer(command);
        }
    }

    @Nested
    @DisplayName("Consumer controller error cases tests")
    class ErrorCases {

        @Test
        void testRegisterConsumer() throws Exception {

            var newConsumer = new NewConsumer("", 0, LocalDate.now());
            var command = RegisterConsumerCommand.of(newConsumer);

            given(registerConsumerUseCase.registerConsumer(command))
                    .willReturn(new ConsumerApiModel());

            mockMvc.perform(post("/consumers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newConsumer)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.consumerId").doesNotExist());

            then(registerConsumerUseCase).shouldHaveNoInteractions();
        }

        @Test
        @DisplayName("Consumer controller error cases tests")
        void testUpdateConsumer() throws Exception {

            var consumerId = 1;
            var newConsumer = new UpdateConsumer("", 12345,
                    LocalDate.of(1985, Month.JANUARY, 1));

            var command = UpdateConsumerCommand.of(consumerId, newConsumer);

            given(updateConsumerUseCase.updateConsumer(command))
                    .willReturn(new ConsumerApiModel());

            mockMvc.perform(put("/consumers/{consumerId}", consumerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newConsumer)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.consumerId").doesNotExist());

            then(updateConsumerUseCase).shouldHaveNoInteractions();
        }
    }
}