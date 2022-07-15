package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.model.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.web.vo.card.UpdateCardFormVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class CardServiceImplTest {

    private static final String NOT_FOUND_MESSAGE = "Card not found!";

    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private CardServiceImpl cardService;

    @Test
    void findByCardNumber_WithInvalidNumber_ShouldThrowResourceNotFoundException() {
        given(cardRepository.findByCardNumber(CARD_INVALID_NUMBER)).willReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            cardService.findByCardNumber(CARD_INVALID_NUMBER);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUND_MESSAGE));
    }

    @Test
    void findByCardNumber_WithValidNumber_ShouldReturnCorrespondingCard() {
        Card card = buildCard(EstablishmentType.FOOD, null);

        given(cardRepository.findByCardNumber(CARD_NUMBER)).willReturn(Optional.of(card));

        Card cardResult = cardService.findByCardNumber(CARD_NUMBER);

        assertNotNull(cardResult);
        assertEquals(card.getId(), cardResult.getId());
        assertEquals(card.getNumber(), cardResult.getNumber());
        assertEquals(0, card.getBalance().compareTo(cardResult.getBalance()));
        assertEquals(card.getType(), cardResult.getType());
    }

    @Test
    void updateCardBalance_WithInvalidNumber_ShouldReturnCardNotFound() {
        given(cardRepository.findByCardNumber(CARD_NUMBER)).willReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            cardService.updateCardBalance(CARD_NUMBER, new UpdateCardFormVO(BigDecimal.valueOf(100.00)));
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUND_MESSAGE));
    }

    @Test
    void updateCardBalance_WithValidNumberAndValue_ShouldAddValueToCardBalance() {
        Card card = buildCard(EstablishmentType.FOOD, null);

        BigDecimal CARD_BALANCE = card.getBalance();
        BigDecimal VALUE_TO_UPDATE = BigDecimal.valueOf(100.00);

        UpdateCardFormVO form = new UpdateCardFormVO(VALUE_TO_UPDATE);

        given(cardRepository.findByCardNumber(CARD_NUMBER)).willReturn(Optional.of(card));
        given(cardRepository.save(any(Card.class))).willReturn(card);

        Card carsResult = cardService.updateCardBalance(CARD_NUMBER, form);

        assertNotNull(carsResult);
        assertEquals(card.getId(), carsResult.getId());
        assertEquals(card.getNumber(), carsResult.getNumber());
        assertEquals(0, CARD_BALANCE.add(VALUE_TO_UPDATE).compareTo(carsResult.getBalance()));
        assertEquals(card.getType(), carsResult.getType());
    }

}