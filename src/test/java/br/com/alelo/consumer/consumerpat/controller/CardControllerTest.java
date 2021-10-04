package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardControllerTest {

    private static final String FOOD_CARD_NUMBER = "1111-2222-3333-4444";
    private static final double VALUE = 1000.00;

    private Card card;

    @InjectMocks
    private CardController controller;

    @Mock
    private CardService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCard();
    }

    @Test
    void WhenSetBalanceEndpointThenReturnSuccessOperation() {

        when(service.setBalance(anyString(), any())).thenReturn(card);

        ResponseEntity<Card> response = controller.setBalance(FOOD_CARD_NUMBER, VALUE);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Card.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(CardType.FOOD.getDescription(), response.getBody().getCardType().getDescription());
    }

    @Test
    void buy() {
    }

    private void startCard() {
        card = new Card(1, FOOD_CARD_NUMBER, VALUE, CardType.FOOD, new Consumer());
    }
}