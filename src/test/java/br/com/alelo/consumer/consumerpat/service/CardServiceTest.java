package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.repository.ICardRepository;
import br.com.alelo.consumer.consumerpat.service.validator.CardValidator;
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

    @Autowired
    private CardService service;

    @MockBean
    private CardValidator validator;

    @MockBean
    private ICardRepository repository;

    private Card cardFood;
    private Card cardDrugstore;
    private Card cardFuel;

    private RequestBuyDTO dtoFood;
    private RequestBuyDTO dtoDrugstore;
    private RequestBuyDTO dtoFuel;

    @BeforeEach
    void setup() {
        cardFood = Card.builder()
           .id(1L)
           .cardType(FOOD)
           .number(1L)
           .balance(BigDecimal.valueOf(100))
           .build();

        cardDrugstore = Card.builder()
                .id(2L)
                .cardType(DRUGSTORE)
                .number(2L)
                .balance(BigDecimal.valueOf(110))
                .build();

        cardFuel = Card.builder()
                .id(3L)
                .cardType(FUEL)
                .number(3L)
                .balance(BigDecimal.valueOf(120))
                .build();

        dtoFood = RequestBuyDTO.builder()
                .establishmentType(FOOD)
                .cardNumber(1L)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();

        dtoDrugstore = RequestBuyDTO.builder()
                .establishmentType(DRUGSTORE)
                .cardNumber(2L)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();

        dtoFuel = RequestBuyDTO.builder()
                .establishmentType(FUEL)
                .cardNumber(3L)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();
    }

    @Test
    void updateBalance_whenValuesOkForFood_ThenReturnNewBalance() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardFood);
        when(repository.save(any())).thenReturn(cardFood);

        var cardUpdated = service.updateBalance(1L, BigDecimal.TEN);

        assertEquals(BigDecimal.valueOf(110), cardUpdated.getBalance());
    }

    @Test
    void updateBalance_whenValuesOkForDrugstore_ThenReturnNewBalance() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardDrugstore);
        when(repository.save(any())).thenReturn(cardDrugstore);

        var cardUpdated = service.updateBalance(1L, BigDecimal.TEN);

        assertEquals(BigDecimal.valueOf(120), cardUpdated.getBalance());
    }

    @Test
    void updateBalance_whenValuesOkForFuel_ThenReturnNewBalance() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardFuel);
        when(repository.save(any())).thenReturn(cardFuel);

        var cardUpdated = service.updateBalance(1L, BigDecimal.TEN);

        assertEquals(BigDecimal.valueOf(130), cardUpdated.getBalance());
    }

    @Test
    void updateBalance_whenValidatorUpThrow_ThenReturnResponseStatusException() {
        when(validator.validCard(anyLong(), any())).thenThrow(ResponseStatusException.class);
        when(repository.save(any())).thenReturn(cardFuel);

        assertThrows(ResponseStatusException.class, () -> service.updateBalance(1L, BigDecimal.TEN));
    }

    @Test
    void buy_whenValuesOkForFood_ThenReturnNewBalanceForCard() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardFood);
        when(validator.getAdditionForBalanceByType(any(), any(), any())).thenReturn(BigDecimal.TEN);
        when(repository.save(any())).thenReturn(cardFood);

        final Card cardUpdated = service.buy(dtoFood);

        assertEquals(BigDecimal.TEN, cardUpdated.getBalance());
    }

    @Test
    void buy_whenValuesOkForDrugstore_ThenReturnNewBalanceForCard() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardDrugstore);
        when(validator.getAdditionForBalanceByType(any(), any(), any())).thenReturn(BigDecimal.ONE);
        when(repository.save(any())).thenReturn(cardDrugstore);

        final Card cardUpdated = service.buy(dtoDrugstore);

        assertEquals(BigDecimal.ONE, cardUpdated.getBalance());
    }

    @Test
    void buy_whenValuesOkForFuel_ThenReturnNewBalanceForCard() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardFuel);
        when(validator.getAdditionForBalanceByType(any(), any(), any())).thenReturn(BigDecimal.ZERO);
        when(repository.save(any())).thenReturn(cardFuel);

        final Card cardUpdated = service.buy(dtoFuel);

        assertEquals(BigDecimal.ZERO, cardUpdated.getBalance());
    }

    @Test
    void buy_whenValuesFailedForFood_ThenReturnResponseStatusException() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardFood);
        when(validator.getAdditionForBalanceByType(any(), any(), any())).thenThrow(ResponseStatusException.class);
        when(repository.save(any())).thenReturn(cardFood);

        assertThrows(ResponseStatusException.class, () -> service.buy(dtoFood));
    }

    @Test
    void buy_whenValuesFailedForDrugstore_ThenReturnResponseStatusException() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardDrugstore);
        when(validator.getAdditionForBalanceByType(any(), any(), any())).thenThrow(ResponseStatusException.class);
        when(repository.save(any())).thenReturn(cardDrugstore);

        assertThrows(ResponseStatusException.class, () -> service.buy(dtoDrugstore));
    }

    @Test
    void buy_whenValuesFailedForFuel_ThenReturnResponseStatusException() {
        when(validator.validCard(anyLong(), any())).thenReturn(cardFuel);
        when(validator.getAdditionForBalanceByType(any(), any(), any())).thenThrow(ResponseStatusException.class);
        when(repository.save(any())).thenReturn(cardFuel);

        assertThrows(ResponseStatusException.class, () -> service.buy(dtoFuel));
    }
}
