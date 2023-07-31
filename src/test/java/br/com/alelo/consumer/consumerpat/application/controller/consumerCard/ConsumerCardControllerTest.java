package br.com.alelo.consumer.consumerpat.application.controller.consumerCard;

import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload.CardConsumerRequest;
import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ConsumerCardControllerTest {

    @InjectMocks
    private ConsumerCardController consumerCardController;

    @Mock
    private CardService cardService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consumerCardController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testAddCard_Success() throws Exception {
        UUID consumerId = UUID.randomUUID();
        Card card = new Card(new CardNumber("1234567890123456"), CardType.FOOD);
        CardConsumerRequest cardRequest = new CardConsumerRequest(card);

        mockMvc.perform(post("/consumers/{consumerId}/cards", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(cardRequest)))
                .andExpect(status().isNoContent());

        then(cardService).should().addCard(consumerId, cardRequest.getCard());
    }

    @Test
    public void testListCardByConsumerId_Success() throws Exception {
        UUID consumerId = UUID.randomUUID();
        var cardNumber = new CardNumber("1234567890123456");
        Card card = new Card(cardNumber, CardType.FOOD);
        card.addCardBalance(new CardBalance(cardNumber));
        Set<Card> cards = new HashSet<>();
        cards.add(card);

        when(cardService.searchCardByConsumerId(consumerId)).thenReturn(Optional.of(cards));

        mockMvc.perform(get("/consumers/{consumerId}/cards", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cards[0].car.cardNumber.cardNumber").value(cardNumber.getCardNumber()))
                .andExpect(jsonPath("$.cards[0].car.cardType").value("FOOD"));
    }
}
