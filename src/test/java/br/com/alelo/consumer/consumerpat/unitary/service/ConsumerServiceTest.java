package br.com.alelo.consumer.consumerpat.unitary.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import br.com.alelo.consumer.consumerpat.exception.CardBalanceChangeNotAllowedException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @InjectMocks
    private ConsumerService consumerService;

    @Test
    void testUpdateConsumer() {

        Consumer savedConsumer = Consumer.builder()
                .id(1L)
                .name("Fulano")
                .documentNumber(123)
                .build();

        Card savedCard = Card.builder()
                .id(1L)
                .typeCard(TypeCard.FOOD)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        savedConsumer.setCardList(Collections.singleton(savedCard));

        when(consumerService.findById(any(Long.class))).thenReturn(Optional.of(savedConsumer));

        Consumer updateConsumer = Consumer.builder()
                .id(1L)
                .name("Fulano")
                .documentNumber(123)
                .build();

        Card updateCard = Card.builder()
                .id(1L)
                .typeCard(TypeCard.FOOD)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.ONE)
                .build();

        updateConsumer.setCardList(Collections.singleton(updateCard));

        assertThrows(CardBalanceChangeNotAllowedException.class, () -> consumerService.update(1L, updateConsumer));
    }
}