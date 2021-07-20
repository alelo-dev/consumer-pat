package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.validation.CardValidator;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.enums.CardType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardServiceTest {

    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @MockBean
    private CardValidator cardValidator;

    private Card cardFood;
    private Card cardDrugstore;
    private Card cardFuel;

    private BuyDTO dtoFood;
    private BuyDTO dtoDrugstore;
    private BuyDTO dtoFuel;

    @BeforeEach
    void setup() {
        cardFood = Card.builder()
           .id(1L)
           .cardType(FOOD)
           .number(1L)
           .balance(BigDecimal.valueOf(920))
           .build();

        cardDrugstore = Card.builder()
                .id(2L)
                .cardType(DRUGSTORE)
                .number(2L)
                .balance(BigDecimal.valueOf(730))
                .build();

        cardFuel = Card.builder()
                .id(3L)
                .cardType(FUEL)
                .number(3L)
                .balance(BigDecimal.valueOf(564))
                .build();

        dtoFood = BuyDTO.builder()
                .establishmentType(FOOD)
                .cardNumber(1L)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();

        dtoDrugstore = BuyDTO.builder()
                .establishmentType(DRUGSTORE)
                .cardNumber(2L)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();

        dtoFuel = BuyDTO.builder()
                .establishmentType(FUEL)
                .cardNumber(3L)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();
    }

    @Test
    void Update_balance_when_value_is_ok_for_food() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardFood);
        when(cardRepository.save(any())).thenReturn(cardFood);

        var cardUpdated = cardService.updateBalance(1L, BigDecimal.TEN);

        assertEquals(BigDecimal.valueOf(930), cardUpdated.getBalance());
    }

    @Test
    void Buy_when_values_are_ok_for_food() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardFood);
        when(cardValidator.getNewBalance(any(), any(), any())).thenReturn(BigDecimal.TEN);
        when(cardRepository.save(any())).thenReturn(cardFood);

        final Card cardUpdated = cardService.buy(dtoFood);

        assertEquals(BigDecimal.TEN, cardUpdated.getBalance());
    }

    @Test
    void Buy_when_values_failed_for_food() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardFood);
        when(cardValidator.getNewBalance(any(), any(), any())).thenThrow(ResponseStatusException.class);
        when(cardRepository.save(any())).thenReturn(cardFood);

        assertThrows(ResponseStatusException.class, () -> cardService.buy(dtoFood));
    }

    @Test
    void Update_balance_when_value_is_ok_for_drugstore() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardDrugstore);
        when(cardRepository.save(any())).thenReturn(cardDrugstore);

        var cardUpdated = cardService.updateBalance(1L, BigDecimal.TEN);

        assertEquals(BigDecimal.valueOf(740), cardUpdated.getBalance());
    }

    @Test
    void Buy_when_values_are_ok_for_drugstore() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardDrugstore);
        when(cardValidator.getNewBalance(any(), any(), any())).thenReturn(BigDecimal.ONE);
        when(cardRepository.save(any())).thenReturn(cardDrugstore);

        final Card cardUpdated = cardService.buy(dtoDrugstore);

        assertEquals(BigDecimal.ONE, cardUpdated.getBalance());
    }

    @Test
    void Buy_when_values_failed_for_drugstore() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardDrugstore);
        when(cardValidator.getNewBalance(any(), any(), any())).thenThrow(ResponseStatusException.class);
        when(cardRepository.save(any())).thenReturn(cardDrugstore);

        assertThrows(ResponseStatusException.class, () -> cardService.buy(dtoDrugstore));
    }

    @Test
    void Update_balance_when_value_is_ok_for_fuel() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardFuel);
        when(cardRepository.save(any())).thenReturn(cardFuel);

        var cardUpdated = cardService.updateBalance(1L, BigDecimal.TEN);

        assertEquals(BigDecimal.valueOf(574), cardUpdated.getBalance());
    }

    @Test
    void Buy_when_values_are_ok_for_fuel(){
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardFuel);
        when(cardValidator.getNewBalance(any(), any(), any())).thenReturn(BigDecimal.ZERO);
        when(cardRepository.save(any())).thenReturn(cardFuel);

        final Card cardUpdated = cardService.buy(dtoFuel);

        assertEquals(BigDecimal.ZERO, cardUpdated.getBalance());
    }

    @Test
    void Buy_when_values_failed_for_fuel() {
        when(cardValidator.validateCard(anyLong(), any())).thenReturn(cardFuel);
        when(cardValidator.getNewBalance(any(), any(), any())).thenThrow(ResponseStatusException.class);
        when(cardRepository.save(any())).thenReturn(cardFuel);

        assertThrows(ResponseStatusException.class, () -> cardService.buy(dtoFuel));
    }

    @Test
    void Update_balance_when_the_validator_returns_an_error() {
        when(cardValidator.validateCard(anyLong(), any())).thenThrow(ResponseStatusException.class);
        when(cardRepository.save(any())).thenReturn(cardFuel);

        assertThrows(ResponseStatusException.class, () -> cardService.updateBalance(1L, BigDecimal.TEN));
    }
}
