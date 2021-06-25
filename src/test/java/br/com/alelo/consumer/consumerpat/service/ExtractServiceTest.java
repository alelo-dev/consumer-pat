package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.data.model.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import br.com.alelo.consumer.consumerpat.util.exception.InvalidOperationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest()
class ExtractServiceTest {

    private final Long CARD_NUMBER = 1111111111111111L;

    @MockBean
    CardRepository cardRepository;

    @MockBean
    ExtractRepository extractRepository;

    @Autowired
    ExtractService extractService;

    @Test
    void canBuyFood() {
        given(cardRepository.findByFoodCardNumberAndType(CARD_NUMBER, TypeCardsEnum.FOOD.getValue()))
                .willReturn(Optional.of(new Card(1, CARD_NUMBER, new BigDecimal(100), TypeCardsEnum.FOOD, null)));

        var extract = extractService.buy(TypeCardsEnum.FOOD.getValue(), CARD_NUMBER, new BigDecimal(100),
                "Padoca", "Pão");
        assertEquals(extract.getAmount().doubleValue(), 90.0);
        assertNotNull(extract.getDateBuy());
        assertEquals(extract.getEstablishmentName(), "Padoca");
    }

    @Test
    void canBuyFuel() {
        given(cardRepository.findByFoodCardNumberAndType(CARD_NUMBER, TypeCardsEnum.FUEL.getValue()))
                .willReturn(Optional.of(new Card(1, CARD_NUMBER, new BigDecimal(1000), TypeCardsEnum.FUEL, null)));

        var extract = extractService.buy(TypeCardsEnum.FUEL.getValue(), CARD_NUMBER, new BigDecimal(100),
                "Posto Ipiranga", "Gasolina");
        assertEquals(extract.getAmount().doubleValue(), 135.0);
        assertNotNull(extract.getDateBuy());
        assertEquals(extract.getEstablishmentName(), "Posto Ipiranga");
    }

    @Test
    void canBuyDrugstore() {
        given(cardRepository.findByFoodCardNumberAndType(CARD_NUMBER, TypeCardsEnum.DRUGSTORE.getValue()))
                .willReturn(Optional.of(new Card(1, CARD_NUMBER, new BigDecimal(100), TypeCardsEnum.DRUGSTORE, null)));

        var extract = extractService.buy(TypeCardsEnum.DRUGSTORE.getValue(), CARD_NUMBER, new BigDecimal(100),
                "Drogaria", "Vitamina");
        assertEquals(extract.getAmount().doubleValue(), 100.0);
        assertNotNull(extract.getDateBuy());
        assertEquals(extract.getEstablishmentName(), "Drogaria");
    }

    @Test
    void failBuy_InsuficientBalance(){
        given(cardRepository.findByFoodCardNumberAndType(CARD_NUMBER, TypeCardsEnum.FUEL.getValue()))
                .willReturn(Optional.of(new Card(1, CARD_NUMBER, new BigDecimal(100), TypeCardsEnum.FUEL, null)));

        try {
           extractService.buy(TypeCardsEnum.FUEL.getValue(), CARD_NUMBER, new BigDecimal(100),
                    "Drogaria", "Vitamina");
           fail();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidOperationException);
            assertTrue(e.getMessage().equals("Insufficient balance."));
        }

    }

    @Test
    void failBuy_CardNotFound(){
        given(cardRepository.findByFoodCardNumberAndType(CARD_NUMBER, TypeCardsEnum.DRUGSTORE.getValue()))
                .willReturn(Optional.of(new Card(1, CARD_NUMBER, new BigDecimal(100), TypeCardsEnum.DRUGSTORE, null)));

        try {
            extractService.buy(TypeCardsEnum.FUEL.getValue(), CARD_NUMBER, new BigDecimal(100),
                    "Drogaria", "Vitamina");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidOperationException);
            assertTrue(e.getMessage().equals("Card not found Establishment Type"));
        }

    }

}