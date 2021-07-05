package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.helper.ConsumerHelper;
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
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private ConsumerService consumerService;

    @Test
    void shouldNotFound() throws ApiException {
        ApiException exception = Assertions.assertThrows(ApiException.class, () -> cardService.creditBalance(123L, new BigDecimal("50.0")));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        Assertions.assertEquals(Code.INVALID_NOT_FOUND, exception.getCode());
    }


    @Test
    void shouldCreditBalance() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());

        Card card = cardService.creditBalance(consumer.getCards().stream().findFirst().get().getNumber(), new BigDecimal("50.0"));

        BigDecimal expected = consumer.getCards().stream().findFirst().get().getBalance().add(new BigDecimal("50.0"));

        Assertions.assertEquals(0, expected.compareTo(card.getBalance()));
    }


}
