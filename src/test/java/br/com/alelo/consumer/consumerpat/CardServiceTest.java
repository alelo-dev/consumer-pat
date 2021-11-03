package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.exception.CardNotAcceptedException;
import br.com.alelo.consumer.consumerpat.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class CardServiceTest {
    @Autowired
    private CardService service;

    @Autowired
    private CardRepository repository;

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private ConsumerService consumerService;

    private Card FOOD_CARD;
    private Card FUEL_CARD;

    private List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        startCards();
    }

    @Test
    void whenSetBalanceReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> service.setBalance("999999",20.0));
    }

    @Test
    void whenSetBalanceSuccess(){
        service.save(FOOD_CARD);
        FOOD_CARD.setCardBalance(20.0);
        assertEquals(20.0,FOOD_CARD.getCardBalance());
    }

    @Test
    void whenBuyReturnNoSuchElementException(){
        Purchase purchase = new Purchase(1,"test",anyString(),"test",20.0);
        assertThrows(NoSuchElementException.class, () -> service.buy(purchase));
    }

    @Test
    void whenBuyReturnInsufficientFundsException() throws InsufficientFundsException, CardNotAcceptedException {
        Purchase purchase = new Purchase(3,"test","3","test",1000.0);
        service.save(FUEL_CARD);
        assertThrows(InsufficientFundsException.class, () -> service.buy(purchase));
    }

    @Test
    void whenBuyReturnSuccess() throws InsufficientFundsException, CardNotAcceptedException {
        Purchase purchase = new Purchase(3,"test","3","test",10.0);
        service.save(FUEL_CARD);
        Card response = service.buy(purchase);
        assertEquals(186.5, response.getCardBalance());
    }


    private void startCards() {
        FOOD_CARD = new Card(null, 1, CardType.FOOD, "1", 500.0);
        FUEL_CARD = new Card(null, 3, CardType.FUEL, "3", 200.0);
    }

}