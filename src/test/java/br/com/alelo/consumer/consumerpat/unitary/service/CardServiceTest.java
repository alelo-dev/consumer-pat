package br.com.alelo.consumer.consumerpat.unitary.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import br.com.alelo.consumer.consumerpat.enums.TypeEstablishment;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ExtractService extractService;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(cardService, "foodCashback", new BigDecimal("0.1"));
        ReflectionTestUtils.setField(cardService, "fuelTax", new BigDecimal("0.35"));
    }

    @Test
    public void testSaveCard() {

        Card card = Card.builder()
                .typeCard(TypeCard.FOOD)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        when(cardRepository.save(any(Card.class))).then(returnsFirstArg());
        Card savedCard = cardService.save(card);
        assertNotNull(savedCard);
    }

    @Test
    public void testSaveCardNotEnoughBalance() {

        Card card = Card.builder()
                .cardBalance(new BigDecimal(-10))
                .build();

        assertThrows(BusinessException.class, () -> cardService.save(card));
    }

    @Test
    public void testUpdateBalanceCardNotFound() {

        when(cardRepository.findByCardNumber(any(Long.class))).thenReturn(null);
        assertThrows(CardNotFoundException.class, () -> cardService.updateBalance(123, BigDecimal.ONE));
    }

    @Test
    public void testBuy() {

        Card card = Card.builder()
                .typeCard(TypeCard.FOOD)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        when(cardRepository.findByCardNumber(any(Long.class))).thenReturn(card);
        when(extractService.save(any(Extract.class))).then(returnsFirstArg());

        BuyDTO buyDTO = BuyDTO.builder()
                .value(BigDecimal.TEN)
                .cardNumber(card.getCardNumber())
                .productDescription("Product")
                .typeEstablishment(TypeEstablishment.FOOD)
                .build();

        cardService.buy(buyDTO);
    }

    @Test
    public void testApplyPercentageFood() {

        Card card = Card.builder()
                .typeCard(TypeCard.FOOD)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        BuyDTO buyDTO = BuyDTO.builder()
                .value(BigDecimal.ONE)
                .cardNumber(card.getCardNumber())
                .productDescription("Product")
                .typeEstablishment(TypeEstablishment.FOOD)
                .build();

        invokeMethod(cardService, "applyPercentage", buyDTO, card);

        assertEquals(new BigDecimal("9.1"), card.getCardBalance());
    }

    @Test
    public void testApplyPercentageFuel() {

        Card card = Card.builder()
                .typeCard(TypeCard.FUEL)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        BuyDTO buyDTO = BuyDTO.builder()
                .value(BigDecimal.ONE)
                .cardNumber(card.getCardNumber())
                .productDescription("Product")
                .typeEstablishment(TypeEstablishment.FUEL)
                .build();

        invokeMethod(cardService, "applyPercentage", buyDTO, card);

        assertEquals(new BigDecimal("8.65"), card.getCardBalance());
    }

    @Test
    public void testApplyPercentageDrugstore() {

        Card card = Card.builder()
                .typeCard(TypeCard.DRUGSTORE)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        BuyDTO buyDTO = BuyDTO.builder()
                .value(BigDecimal.ONE)
                .cardNumber(card.getCardNumber())
                .productDescription("Product")
                .typeEstablishment(TypeEstablishment.DRUGSTORE)
                .build();

        invokeMethod(cardService, "applyPercentage", buyDTO, card);

        assertEquals(new BigDecimal("9"), card.getCardBalance());
    }

    @Test
    public void cardPreValidationsEmptyCard() {

        assertThrows(CardNotFoundException.class, () -> invokeMethod(cardService, "preValidations", new BuyDTO(), null));
    }

    @Test
    public void cardPurchaseWithDifferentEstablishment() {

        Card card = Card.builder()
                .typeCard(TypeCard.DRUGSTORE)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        BuyDTO buyDTO = BuyDTO.builder()
                .value(BigDecimal.ONE)
                .cardNumber(card.getCardNumber())
                .productDescription("Product")
                .typeEstablishment(TypeEstablishment.FUEL)
                .build();

        assertThrows(BusinessException.class, () -> invokeMethod(cardService, "preValidations", buyDTO, card));
    }

}