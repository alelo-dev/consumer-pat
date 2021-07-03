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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExtractServiceTest {

    @Autowired
    private ExtractService extractService;

    @Autowired
    private ConsumerService consumerService;

    @Test
    void shouldNotFound() throws ApiException {
        ApiException exception = Assertions.assertThrows(ApiException.class, () -> extractService.buy(ExtractHelper.buildExtract(CardHelper.buildCard(CardType.FOOD), 15.0)));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        Assertions.assertEquals(Code.INVALID_NOT_FOUND, exception.getCode());
    }

    @Test
    void shouldInvalidRefund() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());

        Card cardFood = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.FOOD)).findFirst().get();

        ApiException exception = Assertions.assertThrows(ApiException.class, () -> extractService.buy(ExtractHelper.buildExtract(cardFood, 15.0)));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals(Code.INVALID_REFUND, exception.getCode());
    }


    @Test
    void shouldVCalidateAndBuyAllCards() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());

        Card cardFood = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.FOOD)).findFirst().get();
        Card cardFuel = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.FUEL)).findFirst().get();
        Card cardDrugStore = consumer.getCards().stream().filter(card -> card.getType().equals(CardType.DRUGSTORE)).findFirst().get();

        Extract extractFood = extractService.buy(ExtractHelper.buildExtract(cardFood, 5.0));

        Extract extractFuel = extractService.buy(ExtractHelper.buildExtract(cardFuel, 3.0));

        Extract extractDrugStore = extractService.buy(ExtractHelper.buildExtract(cardDrugStore, 1.0));

        Double expectedFood = cardFood.getBalance() - (extractFood.getValue() - (extractFood.getValue() * 0.1));
        Double expectedFuel = cardFuel.getBalance() - (extractFuel.getValue() - (extractFuel.getValue() * 0.35));
        Double expectedDrugStore = cardDrugStore.getBalance() - (extractDrugStore.getValue() - (extractDrugStore.getValue() * 0.1));

        Assertions.assertEquals(expectedFood, extractFood.getCards().stream().findFirst().get().getBalance());
        Assertions.assertEquals(expectedFuel, extractFuel.getCards().stream().findFirst().get().getBalance());
        Assertions.assertEquals(expectedDrugStore, extractDrugStore.getCards().stream().findFirst().get().getBalance());
    }

}
