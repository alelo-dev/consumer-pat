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
    public void testUpdateConsumer() {

        Consumer savedConsumer = new Consumer();
        savedConsumer.setId(1L);
        savedConsumer.setName("Fulano");
        savedConsumer.setDocumentNumber(123);

        Card savedCard = new Card();
        savedCard.setId(1L);
        savedCard.setTypeCard(TypeCard.FOOD);
        savedCard.setCardNumber(1234123412341234L);
        savedCard.setCardBalance(BigDecimal.TEN);
        savedConsumer.setCardList(Collections.singleton(savedCard));

        when(consumerService.findById(any(Long.class))).thenReturn(Optional.of(savedConsumer));

        Consumer updateConsumer = new Consumer();
        updateConsumer.setId(1L);
        updateConsumer.setName("Fulano");
        updateConsumer.setDocumentNumber(123);

        Card updateCard = new Card();
        updateCard.setId(1L);
        updateCard.setTypeCard(TypeCard.FOOD);
        updateCard.setCardNumber(1234123412341234L);
        updateCard.setCardBalance(BigDecimal.ONE);
        updateConsumer.setCardList(Collections.singleton(updateCard));

        assertThrows(CardBalanceChangeNotAllowedException.class, () -> consumerService.update(1L, updateConsumer));
    }
}