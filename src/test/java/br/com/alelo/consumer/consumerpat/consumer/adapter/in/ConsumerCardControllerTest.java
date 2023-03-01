package br.com.alelo.consumer.consumerpat.consumer.adapter.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.AddCardCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.AddCardUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ConsumerCardController.class)
class ConsumerCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddCardUseCase addCardUseCase;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("Consumer card controller success cases tests")
    class SuccessCases {

        @Test
        void testAddCard() throws Exception {

            var consumerId = 1;
            var newCard = new NewCard("1111111111111111", CardTypeEnum.FOOD.getValue());
            var command = AddCardCommand.of(consumerId, newCard);

            mockMvc.perform(post("/consumers/{consumerId}/cards", consumerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newCard)))
                    .andExpect(status().isNoContent());

            then(addCardUseCase).should()
                    .addCard(command);
        }
    }

    @Nested
    @DisplayName("Consumer card controller error cases tests")
    class ErrorCases {

        @Test
        void testAddCard() throws Exception {

            var consumerId = 1;
            var newCard = new NewCard("111111111111111b", CardTypeEnum.FOOD.getValue());
            var command = AddCardCommand.of(consumerId, newCard);

            mockMvc.perform(post("/consumers/{consumerId}/cards", consumerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newCard)))
                    .andExpect(status().isBadRequest());

            then(addCardUseCase).shouldHaveNoInteractions();
        }
    }
}