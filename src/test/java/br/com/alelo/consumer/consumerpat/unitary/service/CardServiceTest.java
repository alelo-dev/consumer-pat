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

        Card card = new Card();
        card.setTypeCard(TypeCard.FOOD);
        card.setCardNumber(1234123412341234L);
        card.setCardBalance(BigDecimal.TEN);

        when(cardRepository.save(any(Card.class))).then(returnsFirstArg());
        Card savedCard = cardService.save(card);
        assertNotNull(savedCard);
    }

    @Test
    public void testSaveCardNotEnoughBalance() {

        Card card = new Card();
        card.setCardBalance(new BigDecimal(-10));

        assertThrows(BusinessException.class, () -> cardService.save(card));
    }

    @Test
    public void testUpdateBalanceCardNotFound() {

        when(cardRepository.findByCardNumber(any(Long.class))).thenReturn(null);
        assertThrows(CardNotFoundException.class, () -> cardService.updateBalance(123, BigDecimal.ONE));
    }

    @Test
    public void testBuy() {

        Card card = new Card();
        card.setTypeCard(TypeCard.FOOD);
        card.setCardNumber(1234123412341234L);
        card.setCardBalance(BigDecimal.TEN);

        when(cardRepository.findByCardNumber(any(Long.class))).thenReturn(card);
        when(extractService.save(any(Extract.class))).then(returnsFirstArg());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(BigDecimal.TEN);
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setProductDescription("Product");
        buyDTO.setTypeEstablishment(TypeEstablishment.FOOD);
        cardService.buy(buyDTO);
    }

    @Test
    public void testApplyPercentageFood() {

        Card card = new Card();
        card.setTypeCard(TypeCard.FOOD);
        card.setCardNumber(1234123412341234L);
        card.setCardBalance(BigDecimal.TEN);

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(BigDecimal.ONE);
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setProductDescription("Product");
        buyDTO.setTypeEstablishment(TypeEstablishment.FOOD);

        invokeMethod(cardService, "applyPercentage", buyDTO, card);

        assertEquals(new BigDecimal("9.1"), card.getCardBalance());
    }

    @Test
    public void testApplyPercentageFuel() {

        Card card = new Card();
        card.setTypeCard(TypeCard.FUEL);
        card.setCardNumber(1234123412341234L);
        card.setCardBalance(BigDecimal.TEN);

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(BigDecimal.ONE);
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setProductDescription("Product");
        buyDTO.setTypeEstablishment(TypeEstablishment.FUEL);

        invokeMethod(cardService, "applyPercentage", buyDTO, card);

        assertEquals(new BigDecimal("8.65"), card.getCardBalance());
    }

    @Test
    public void testApplyPercentageDrugstore() {

        Card card = new Card();
        card.setTypeCard(TypeCard.DRUGSTORE);
        card.setCardNumber(1234123412341234L);
        card.setCardBalance(BigDecimal.TEN);

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(BigDecimal.ONE);
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setProductDescription("Product");
        buyDTO.setTypeEstablishment(TypeEstablishment.DRUGSTORE);

        invokeMethod(cardService, "applyPercentage", buyDTO, card);

        assertEquals(new BigDecimal("9"), card.getCardBalance());
    }

    @Test
    public void cardPreValidationsEmptyCard() {

        assertThrows(CardNotFoundException.class, () -> invokeMethod(cardService, "preValidations", new BuyDTO(), null));
    }

    @Test
    public void cardPurchaseWithDifferentEstablishment() {

        Card card = new Card();
        card.setTypeCard(TypeCard.DRUGSTORE);
        card.setCardNumber(1234123412341234L);
        card.setCardBalance(BigDecimal.TEN);

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(BigDecimal.ONE);
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setProductDescription("Product");
        buyDTO.setTypeEstablishment(TypeEstablishment.FUEL);

        assertThrows(BusinessException.class, () -> invokeMethod(cardService, "preValidations", buyDTO, card));
    }

}