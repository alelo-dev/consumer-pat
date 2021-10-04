package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.exceptions.IllegalArgumentException;
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

    private static final double VALUE                    = 1000.00;
    private static final String FOOD_CARD_NUMBER         = "1111-2222-3333-4444";
    private static final String FUEL_CARD_NUMBER         = "1111-2222-3333-4444";
    private static final String DRUG_STORE_CARD_NUMBER   = "1111-2222-3333-4444";
    private static final String OBJECT_NOT_FOUND_MESSAGE = "Objeto Não encontrado. Tipo: " + Card.class.getSimpleName();
    private static final String CARTAO_NAO_VALIDO_PARA_ESTE_ESTABELECIMENTO = "Cartão não valido para este estabelecimento";

    @InjectMocks
    private CardService service;

    @Mock
    private CardRepository repository;

    @Mock
    private ExtractRepository extractRepository;

    private Card FOOD_CARD;
    private Card FUEL_CARD;
    private Card DRUG_STORE_CARD;
    private Purchase PURCHASE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCard();
        startPurchase();
    }

    @Test
    void whenSetBalanceThenReturnSuccess() {
        when(repository.findByCardNumber(anyString())).thenReturn(FOOD_CARD);
        when(repository.save(any())).thenReturn(FOOD_CARD);

        Card response = service.setBalance(FOOD_CARD_NUMBER, VALUE);

        assertEquals(Card.class, response.getClass());
        assertEquals(FOOD_CARD_NUMBER, response.getCardNumber());
        assertEquals(VALUE + VALUE, response.getCardBalance());
    }

    @Test
    void whenSetBalanceThenReturnObjectNotFoundException() {
        when(repository.findByCardNumber(anyString()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND_MESSAGE));

        try {
            service.setBalance(FOOD_CARD_NUMBER, VALUE);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_MESSAGE, ex.getMessage());
        }
    }

    @Test
    void whenBuyWithFoodCardThenReturnSuccess() {
        when(repository.findByCardNumber(anyString())).thenReturn(FOOD_CARD);
        when(repository.save(any())).thenReturn(FOOD_CARD);

        Card response = service.buy(PURCHASE);

        assertEquals(Card.class, response.getClass());
        assertEquals(CardType.FOOD, response.getCardType());
        assertEquals((VALUE - VALUE * .9), response.getCardBalance());
    }

    @Test
    void whenBuyWithDrugStoreCardThenReturnSuccess() {
        when(repository.findByCardNumber(anyString())).thenReturn(DRUG_STORE_CARD);
        when(repository.save(any())).thenReturn(DRUG_STORE_CARD);

        PURCHASE.setEstablishmentType(EstablishmentType.DRUG_STORE);
        Card response = service.buy(PURCHASE);

        assertEquals(Card.class, response.getClass());
        assertEquals(CardType.DRUG_STORE, response.getCardType());
        assertEquals(0, response.getCardBalance());
    }

    @Test
    void whenBuyWithFuelCardThenReturnSuccess() {
        when(repository.findByCardNumber(anyString())).thenReturn(FUEL_CARD);
        when(repository.save(any())).thenReturn(FUEL_CARD);

        PURCHASE.setEstablishmentType(EstablishmentType.FUEL);
        Card response = service.buy(PURCHASE);

        assertEquals(Card.class, response.getClass());
        assertEquals(CardType.FUEL, response.getCardType());
        assertEquals((VALUE * .65 - VALUE), response.getCardBalance());
    }

    @Test
    void whenBuyWithTypeCardDifferentOfEstablishmentTypeThenReturnException() {
        when(repository.findByCardNumber(anyString()))
                .thenThrow(new IllegalArgumentException(CARTAO_NAO_VALIDO_PARA_ESTE_ESTABELECIMENTO));

        try{
            service.buy(PURCHASE);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals(CARTAO_NAO_VALIDO_PARA_ESTE_ESTABELECIMENTO, ex.getMessage());
        }

    }

    @Test
    void validIfCardNumberAlreadyExists() {
    }

    private void startCard() {
        FOOD_CARD = new Card(1, FOOD_CARD_NUMBER, VALUE, CardType.FOOD, null);
        FUEL_CARD = new Card(2, FUEL_CARD_NUMBER, VALUE, CardType.FUEL, null);
        DRUG_STORE_CARD = new Card(3, DRUG_STORE_CARD_NUMBER, VALUE, CardType.DRUG_STORE, null);
    }

    private void startPurchase() {
        PURCHASE = new Purchase(
                EstablishmentType.FOOD,
                "Estabelecimento",
                FOOD_CARD_NUMBER,
                "Descrição do produto",
                VALUE);
    }
}