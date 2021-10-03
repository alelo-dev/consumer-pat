package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardServiceTest {

    private static final String CARD_NUMBER = "1111-2222-3333-4444";
    private static final double VALUE = 1000.00;
    private static final String OBJECT_NOT_FOUND_MESSAGE = "Objeto Não encontrado. Tipo: " + Card.class.getSimpleName();
    @InjectMocks
    private CardService service;

    @Mock
    private CardRepository repository;

    @Mock
    private ExtractRepository extractRepository;

    private Card card;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCard();
    }

    @Test
    void whenSetBalanceThenReturnSuccess() {
        when(repository.findByCardNumber(anyString())).thenReturn(card);
        when(repository.save(any())).thenReturn(card);

        Card response = service.setBalance(CARD_NUMBER, VALUE);

        assertEquals(Card.class, response.getClass());
        assertEquals(CARD_NUMBER, response.getCardNumber());
        assertEquals(VALUE, response.getCardBalance());
    }

    @Test
    void whenSetBalanceThenReturnObjectNotFoundException() {
        when(repository.findByCardNumber(anyString()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND_MESSAGE));

        try {
            service.setBalance(CARD_NUMBER, VALUE);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_MESSAGE, ex.getMessage());
        }
    }

    @Test
    void buy() {
    }

    @Test
    void validIfCardNumberAlreadyExists() {
    }

    private void startCard() {
        card = new Card(1, "0000000000000000", VALUE, CardType.FOOD, null);
    }
}