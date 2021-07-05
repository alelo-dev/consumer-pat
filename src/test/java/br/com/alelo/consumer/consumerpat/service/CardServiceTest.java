package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BussinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    public static final long FOOD_CARD_NUMBER = 111L;
    public static final long DRUGSTORE_CARD_NUMBER = 555L;
    public static final long FUEL_CARD_NUMBER = 999L;
    private Card foodCard;
    private Card fuelCard;
    private Card drugstoreCard;

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        foodCard = new Card();
        foodCard.setId(FOOD_CARD_NUMBER);
        foodCard.setBalance(50D);
        foodCard.setType(CardType.FOOD);

        fuelCard = new Card();
        fuelCard.setId(FUEL_CARD_NUMBER);
        fuelCard.setType(CardType.FUEL);
        fuelCard.setBalance(50D);

        drugstoreCard = new Card();
        drugstoreCard.setId(FUEL_CARD_NUMBER);
        drugstoreCard.setType(CardType.DRUGSTORE);
        drugstoreCard.setBalance(50D);
    }

    @Test
    void adicionaSaldoComSucesso() throws BussinessException {
        when(repository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        cardService.setBalance(FOOD_CARD_NUMBER, 50.0);
    }

    @Test
    @ExceptionHandler(BussinessException.class)
    void adicionaSaldoCartaoInvalido() {
        Assertions.assertThrows(BussinessException.class, () -> cardService.setBalance(999L, 50.0));
    }

    @Test
    @ExceptionHandler(BussinessException.class)
    void adicionaSaldoComValorNegativo() {
        Assertions.assertThrows(BussinessException.class, () -> cardService.setBalance(999L, -50.0));
    }

    @Test
    void efetuaUmaCompraFoodComSucesso() throws BussinessException {
        when(repository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        Extract extrato = cardService.buy(1, "Test",
                FOOD_CARD_NUMBER, "Comida", 25.0);

        assertEquals(25.0 * 0.1, extrato.getCashback());
        assertEquals(25.0, extrato.getValue());

    }

    @Test
    void efetuaUmaCompraDrugstoreComSucesso() throws BussinessException {
        when(repository.findByNumber(DRUGSTORE_CARD_NUMBER)).thenReturn(Optional.of(drugstoreCard));
        Extract extrato = cardService.buy(2, "Test",
                DRUGSTORE_CARD_NUMBER, "Remedios", 25.0);

        assertEquals(0.0, extrato.getCashback());
        assertEquals(25.0, extrato.getValue());

    }

    @Test
    void efetuaUmaCompraFuelComSucesso() throws BussinessException {
        when(repository.findByNumber(FUEL_CARD_NUMBER)).thenReturn(Optional.of(fuelCard));
        Extract extrato = cardService.buy(3, "Test",
                FUEL_CARD_NUMBER, "Gasolina", 10.0);

        assertEquals(10.0 * 0.35, extrato.getTaxa());
        assertEquals(10.0, extrato.getValue());

    }

    @Test
    void efetuaUmaCompraComSaldoInsuficiente() {
        when(repository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        Assertions.assertThrows(BussinessException.class, () ->
                cardService.buy(1, "Test", FOOD_CARD_NUMBER, "Comida", 75.0)
        );
    }

    @Test
    void efetuaUmaCompraComEstabeicimentoDiferenteDoCartao() throws BussinessException {
        when(repository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        Assertions.assertThrows(BussinessException.class, () ->
                cardService.buy(2, "Test", FOOD_CARD_NUMBER, "Comida", 25.0)
        );
    }

}
