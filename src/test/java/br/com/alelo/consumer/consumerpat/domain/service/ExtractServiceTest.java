package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.helper.CardHelper;
import br.com.alelo.consumer.consumerpat.helper.ConsumerHelper;
import br.com.alelo.consumer.consumerpat.helper.ExtractHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExtractServiceTest {

    @Autowired
    private ExtractService extractService;

    @Autowired
    private ConsumerService consumerService;

    @Test
    void shouldNotFound() throws ApiException {
        ApiException exception = Assertions.assertThrows(ApiException.class, () -> extractService.buy(ExtractHelper.buildExtract(CardHelper.buildCard(CardType.FOOD), new BigDecimal("15.0"))));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        Assertions.assertEquals(Code.INVALID_NOT_FOUND, exception.getCode());
    }

    @Test
    void shouldInvalidRefund() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());

        Card cardFood = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.FOOD)).findFirst().get();

        ApiException exception = Assertions.assertThrows(ApiException.class, () -> extractService.buy(ExtractHelper.buildExtract(cardFood, new BigDecimal("15.0"))));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals(Code.INVALID_REFUND, exception.getCode());
    }


    @Test
    void shouldVCalidateAndBuyAllCards() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());

        Card cardFood = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.FOOD)).findFirst().get();
        Card cardFuel = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.FUEL)).findFirst().get();
        Card cardDrugStore = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.DRUGSTORE)).findFirst().get();

        Extract extractFood = extractService.buy(ExtractHelper.buildExtract(cardFood, new BigDecimal("5.0")));
        Extract extractFuel = extractService.buy(ExtractHelper.buildExtract(cardFuel, new BigDecimal("3.0")));
        Extract extractDrugStore = extractService.buy(ExtractHelper.buildExtract(cardDrugStore, new BigDecimal("1.0")));

        BigDecimal expectedFood = cardFood.getBalance().subtract(extractFood.getValue().subtract(extractFood.getValue().multiply(new BigDecimal("0.1"))));
        BigDecimal expectedFuel = cardFuel.getBalance().subtract(extractFuel.getValue().subtract(extractFuel.getValue().multiply(new BigDecimal("0.35"))));
        BigDecimal expectedDrugStore = cardDrugStore.getBalance().subtract(extractDrugStore.getValue().subtract(extractDrugStore.getValue().multiply(new BigDecimal("0.1"))));

        Assertions.assertEquals(0, expectedFood.compareTo(extractFood.getCards().stream().findFirst().get().getBalance()));
        Assertions.assertEquals(0, expectedFuel.compareTo(extractFuel.getCards().stream().findFirst().get().getBalance()));
        Assertions.assertEquals(0, expectedDrugStore.compareTo(extractDrugStore.getCards().stream().findFirst().get().getBalance()));
    }

}
