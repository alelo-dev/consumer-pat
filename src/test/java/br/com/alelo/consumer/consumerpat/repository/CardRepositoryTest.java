package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CardRepositoryTest {

    @Mock
    private ICardRepository cardRepository;

    @Test
    void findCardByNumber_WhenNotNull_ThenReturnSuccess() {
        when(cardRepository.findByNumber(anyLong())).thenReturn(Optional.of(Card.builder().build()));

        final Optional<Card> cardRepositoryByNumber = cardRepository.findByNumber(1L);

        assertFalse(cardRepositoryByNumber.isEmpty());
        assertNotNull(cardRepositoryByNumber.get());
    }

    @Test
    void findCardByNumber_WhenCallFind_ThenReturnNotSuccess() {
        when(cardRepository.findByNumber(anyLong())).thenReturn(Optional.empty());

        final Optional<Card> cardRepositoryByNumber = cardRepository.findByNumber(1L);

        assertTrue(cardRepositoryByNumber.isEmpty());
    }
}
